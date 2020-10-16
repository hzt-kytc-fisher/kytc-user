package com.kytc.user.server.impl;

import com.kytc.framework.cache.aop.ClearCache;
import com.kytc.framework.cache.aop.RedisCache;
import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.request.UserInfoSearchRequest;
import com.kytc.user.server.service.UserInfoService;
import com.kytc.user.request.UserInfoRequest;
import com.kytc.user.response.UserInfoResponse;
import com.kytc.user.dao.data.UserInfoData;
import com.kytc.user.dao.mapper.UserInfoMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserInfoServiceImpl implements UserInfoService {
	private final UserInfoMapperEx userInfoMapperEx;

	@Override
	public Long add(UserInfoRequest request){
		UserInfoData userInfoData = BeanUtils.convert(request, UserInfoData.class);
		userInfoData.setCreatedAt(new Date());
		userInfoData.setUpdatedAt(new Date());
		request.setRegisterTime(new Date());
		userInfoData.setIsDeleted(false);
		this.userInfoMapperEx.insert(userInfoData);
		if( null == userInfoData.getId() ){
			throw new BaseException(BaseErrorCodeEnum.SYSTEM_ERROR,"添加用户信息失败");
		}
		return userInfoData.getId();
	}

	@Override
	@ClearCache(cachePreKey = "user:info",key = "#request.id")
	public boolean update(UserInfoRequest request){
		if( null != request ){
			UserInfoData userInfoData = BeanUtils.convert(request, UserInfoData.class);
			userInfoData.setUpdatedAt(new Date());
			return this.userInfoMapperEx.updateByPrimaryKey(userInfoData)>0;
		}
		return false;
	}

	@Override
	@RedisCache(cachePreKey = "user:info",key = "#id")
	public UserInfoResponse detail(Long id){
		UserInfoData userInfoData = this.userInfoMapperEx.selectByPrimaryKey( id );
		if( null == userInfoData ){
			return null;
		}
		return BeanUtils.convert(userInfoData,UserInfoResponse.class);
	}

	@Override
	@ClearCache(cachePreKey = "user:info",key = "#id")
	public boolean delete(Long id){
		UserInfoData userInfoData = new UserInfoData();
		userInfoData.setId(id);
		userInfoData.setIsDeleted(true);
		userInfoData.setUpdatedAt(new Date());
		return this.userInfoMapperEx.updateByPrimaryKeySelective(userInfoData)>0;
	}

	@Override
	public BasePageResponse<UserInfoResponse> listByCondition(UserInfoSearchRequest request){
		BasePageResponse<UserInfoResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	@Override
	public boolean disable(Long userId) {
		return this.userInfoMapperEx.updateEnabled(userId,false)>0;
	}

	@Override
	public boolean enable(Long userId) {
		return this.userInfoMapperEx.updateEnabled(userId,true)>0;
	}

	private List<UserInfoResponse> listByConditionData(UserInfoSearchRequest request){
		request.init();
		List<UserInfoData> list =  this.userInfoMapperEx.listByCondition(request.getUsername(), request.getNickName(),
				request.getIdCard(), request.getEnabled(), request.getMobile(), request.getRegisterTime(),
				request.getStart(),request.getPageSize());
		return BeanUtils.convert(list,UserInfoResponse.class);
	}


	private Long countByConditionData(UserInfoSearchRequest request){
		return this.userInfoMapperEx.countByCondition(request.getUsername(), request.getNickName(),
				request.getIdCard(), request.getEnabled(), request.getMobile(),
				request.getRegisterTime());
	}
}
