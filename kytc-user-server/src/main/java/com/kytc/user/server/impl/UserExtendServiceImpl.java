package com.kytc.user.server.impl;

import com.kytc.framework.cache.aop.ClearCache;
import com.kytc.framework.cache.aop.RedisCache;
import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.request.UserExtendSearchRequest;
import com.kytc.user.server.service.UserExtendService;
import com.kytc.user.request.UserExtendRequest;
import com.kytc.user.response.UserExtendResponse;
import com.kytc.user.dao.data.UserExtendData;
import com.kytc.user.dao.mapper.UserExtendMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserExtendServiceImpl implements UserExtendService {
	private final UserExtendMapperEx userExtendMapperEx;

	@Override
	public Long add(UserExtendRequest request){
		UserExtendResponse response = this.getByUserId(request.getUserId());
		if( null != response ){
			throw new BaseException(BaseErrorCodeEnum.DATA_HAS_EXISTS,"扩展数据已经存在");
		}
		UserExtendData userExtendData = BeanUtils.convert(request, UserExtendData.class);
		userExtendData.setCreatedAt(new Date());
		userExtendData.setUpdatedAt(new Date());
		this.userExtendMapperEx.insert(userExtendData);
		return userExtendData.getId();
	}

	@Override
	public boolean update(UserExtendRequest request){
		if( null != request ){
			UserExtendData userExtendData = BeanUtils.convert(request, UserExtendData.class);
			userExtendData.setUpdatedAt(new Date());
			return this.userExtendMapperEx.updateByPrimaryKey(userExtendData)>0;
		}
		return false;
	}

	@Override
	public UserExtendResponse detail(Long id){
		UserExtendData userExtendData = this.userExtendMapperEx.selectByPrimaryKey( id );
		if( null == userExtendData ){
			return null;
		}
		return BeanUtils.convert(userExtendData,UserExtendResponse.class);
	}

	@Override
	public boolean delete(Long id){
		return this.userExtendMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<UserExtendResponse> listByCondition(UserExtendSearchRequest request){
		BasePageResponse<UserExtendResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	@Override
	@RedisCache(cachePreKey = "user:extend:info",key = "#userId",expireTime = 1)
	public UserExtendResponse getByUserId(Long userId) {
		UserExtendData userExtendData = this.userExtendMapperEx.getByUserId(userId);
		if( null == userExtendData ){
			return null;
		}
		return BeanUtils.convert(userExtendData,UserExtendResponse.class);
	}

	private List<UserExtendResponse> listByConditionData(UserExtendSearchRequest request){
		request.init();
		List<UserExtendData> list =  this.userExtendMapperEx.listByCondition(request.getUserId(), request.getDeptId(),
				request.getStart(),request.getLimit());
		return BeanUtils.convert(list,UserExtendResponse.class);
	}


	private Long countByConditionData(UserExtendSearchRequest request){
		return this.userExtendMapperEx.countByCondition(request.getUserId(), request.getDeptId());
	}
}
