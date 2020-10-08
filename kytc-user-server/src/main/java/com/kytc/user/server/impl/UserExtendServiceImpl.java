package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
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
	public boolean add(UserExtendRequest request){
		if( null != request ){
			UserExtendData userExtendData = BeanUtils.convert(request, UserExtendData.class);
			userExtendData.setCreatedAt(new Date());
			userExtendData.setUpdatedAt(new Date());
			return this.userExtendMapperEx.insert(userExtendData)>0;
		}
		return false;
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
	public BasePageResponse<UserExtendResponse> listByCondition(UserExtendRequest request,int page, int pageSize){
		BasePageResponse<UserExtendResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<UserExtendResponse> listByConditionData(UserExtendRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<UserExtendData> list =  this.userExtendMapperEx.listByCondition(request.getUserId(), request.getDeptId(), start, pageSize);
		return BeanUtils.convert(list,UserExtendResponse.class);
	}


	private Long countByConditionData(UserExtendRequest request){
		return this.userExtendMapperEx.countByCondition(request.getUserId(), request.getDeptId());
	}
}
