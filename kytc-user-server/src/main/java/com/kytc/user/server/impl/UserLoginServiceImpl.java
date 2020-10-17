package com.kytc.user.server.impl;

import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.request.LoginRequest;
import com.kytc.user.request.UserLoginSearchRequest;
import com.kytc.user.response.*;
import com.kytc.user.server.service.*;
import com.kytc.user.request.UserLoginRequest;
import com.kytc.user.dao.data.UserLoginData;
import com.kytc.user.dao.mapper.UserLoginMapperEx;
import com.kytc.user.server.utils.EncryptUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author hezhitong
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserLoginServiceImpl implements UserLoginService {
	private final UserLoginMapperEx userLoginMapperEx;
	private final UserInfoService userInfoService;
	private final UserRoleService userRoleService;
	private final UserPermissionService userPermissionService;
	private final DepartmentService departmentService;

	@Override
	public Long add(UserLoginRequest request){
		UserLoginData data = this.userLoginMapperEx.getByLoginTypeAndKey(request.getLoginType().getValue(),request.getLoginKey());
		if( null != data && data.getUserId().equals(request.getUserId())){
			throw new BaseException(BaseErrorCodeEnum.DATA_HAS_EXISTS,"该用户已绑定该登录方式");
		}else if(null != data && !data.getUserId().equals(request.getUserId())){
			throw new BaseException(BaseErrorCodeEnum.DATA_HAS_EXISTS,"该登录方式已被其他用户占用");
		}
		UserLoginData userLoginData = BeanUtils.convert(request, UserLoginData.class);
		userLoginData.setSalt(RandomStringUtils.randomAlphabetic(64));
		userLoginData.setLoginPassword(EncryptUtils.getInstance().sha(userLoginData.getSalt(),userLoginData.getLoginPassword()));
		userLoginData.setLoginType(request.getLoginType().getValue());
		userLoginData.setCreatedAt(new Date());
		userLoginData.setIsDeleted(false);
		userLoginData.setUpdatedAt(new Date());
		this.userLoginMapperEx.insert(userLoginData);
		if( null == userLoginData.getId() ){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,"添加登录信息失败");
		}
		return userLoginData.getId();
	}

	@Override
	public boolean update(UserLoginRequest request){
		if( null != request ){
			UserLoginData userLoginData = BeanUtils.convert(request, UserLoginData.class);
			userLoginData.setUpdatedAt(new Date());
			return this.userLoginMapperEx.updateByPrimaryKey(userLoginData)>0;
		}
		return false;
	}

	@Override
	public UserLoginResponse detail(Long id){
		UserLoginData userLoginData = this.userLoginMapperEx.selectByPrimaryKey( id );
		if( null == userLoginData ){
			return null;
		}
		return BeanUtils.convert(userLoginData,UserLoginResponse.class);
	}

	@Override
	public boolean delete(Long id){
		UserLoginData userLoginData = new UserLoginData();
		userLoginData.setId(id);
		userLoginData.setIsDeleted(true);
		userLoginData.setUpdatedAt(new Date());
		return this.userLoginMapperEx.updateByPrimaryKeySelective(userLoginData)>0;
	}

	@Override
	public BasePageResponse<UserLoginResponse> listByCondition(UserLoginSearchRequest request){
		BasePageResponse<UserLoginResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	@Override
	public UserResponse login(LoginRequest request) {
		UserLoginData userLoginData = this.userLoginMapperEx.getByLoginTypeAndKey(request.getLoginTypeEnum().getValue(),request.getLoginKey());
		if( null == userLoginData ){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,"用户名或密码错误");
		}
		String password = EncryptUtils.getInstance().sha(userLoginData.getSalt(),request.getPassword());
		if(!userLoginData.getLoginPassword().equals(password)){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,"用户名或密码错误");
		}
		Long userId = userLoginData.getUserId();
		UserInfoResponse userInfoResponse = this.userInfoService.detail(userId);
		if( null == userInfoResponse ){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,"未查找到该用户");
		}
		if( !userInfoResponse.getEnabled() ){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,"该用户被禁用,请联系管理员");
		}
		UserResponse userResponse = BeanUtils.convert(userInfoResponse,UserResponse.class);
		List<UserLoginResponse> loginResponses = this.selectByUserId(userLoginData.getUserId());
		loginResponses = loginResponses.stream().map(login->{
			if(login.getId().equals(userLoginData.getId())){
				login.setIsCurrentLogin(true);
			}else{
				login.setIsCurrentLogin(false);
			}
			return login;
		}).collect(Collectors.toList());
		userResponse.setUserLoginResponses(loginResponses);
		if( userResponse.getDeptId() != -1L ){
			List<DepartmentResponse> list = this.departmentService.select(userResponse.getDeptId());
			if( !CollectionUtils.isEmpty(list) ){
				userResponse.setDepartmentResponse(list.get(0));
			}
		}
		userResponse.setRoles(this.userRoleService.selectByUserId(userId));
		userResponse.setPermissionResponses(this.userPermissionService.selectByUserId(userId));
		List<PermissionResponse> permissions = this.userPermissionService.selectByUserIdAll(userId);
		if(!CollectionUtils.isEmpty(permissions)){
			List<String> pers = permissions.stream().map(PermissionResponse::getPermissionKey).distinct().collect(Collectors.toList());
			userResponse.setPermissions(pers);
		}
		return userResponse;
	}

	@Override
	public List<UserLoginResponse> selectByUserId(Long userId) {
		List<UserLoginData> list = this.userLoginMapperEx.selectByUserId(userId);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return BeanUtils.convert(list,UserLoginResponse.class);
	}

	private List<UserLoginResponse> listByConditionData(UserLoginSearchRequest request){
		request.init();
		List<UserLoginData> list =  this.userLoginMapperEx.listByCondition(request.getLoginType(), request.getLoginKey(),request.getUserId(), request.getStart(), request.getLimit());
		return BeanUtils.convert(list,UserLoginResponse.class);
	}


	private Long countByConditionData(UserLoginSearchRequest request){
		return this.userLoginMapperEx.countByCondition(request.getLoginType(), request.getLoginKey(), request.getUserId());
	}
}
