package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.UserLoginService;
import com.kytc.user.request.UserLoginRequest;
import com.kytc.user.response.UserLoginResponse;
import com.kytc.user.dao.data.UserLoginData;
import com.kytc.user.dao.mapper.UserLoginMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserLoginServiceImpl implements UserLoginService {
	private final UserLoginMapperEx userLoginMapperEx;

	@Override
	public boolean add(UserLoginRequest request){
		if( null != request ){
			UserLoginData userLoginData = BeanUtils.convert(request, UserLoginData.class);
			userLoginData.setCreatedAt(new Date());
			userLoginData.setUpdatedAt(new Date());
			return this.userLoginMapperEx.insert(userLoginData)>0;
		}
		return false;
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
	public BasePageResponse<UserLoginResponse> listByCondition(UserLoginRequest request,int page, int pageSize){
		BasePageResponse<UserLoginResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<UserLoginResponse> listByConditionData(UserLoginRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<UserLoginData> list =  this.userLoginMapperEx.listByCondition(request.getLoginType(), request.getLoginKey(), request.getLoginPassword(), request.getUserId(), request.getIsDeleted(), request.getSalt(), start, pageSize);
		return BeanUtils.convert(list,UserLoginResponse.class);
	}


	private Long countByConditionData(UserLoginRequest request){
		return this.userLoginMapperEx.countByCondition(request.getLoginType(), request.getLoginKey(), request.getLoginPassword(), request.getUserId(), request.getIsDeleted(), request.getSalt());
	}
}
