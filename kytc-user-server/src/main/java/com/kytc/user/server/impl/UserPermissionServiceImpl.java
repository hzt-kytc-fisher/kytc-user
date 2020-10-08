package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.UserPermissionService;
import com.kytc.user.request.UserPermissionRequest;
import com.kytc.user.response.UserPermissionResponse;
import com.kytc.user.dao.data.UserPermissionData;
import com.kytc.user.dao.mapper.UserPermissionMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserPermissionServiceImpl implements UserPermissionService {
	private final UserPermissionMapperEx userPermissionMapperEx;

	@Override
	public boolean add(UserPermissionRequest request){
		if( null != request ){
			UserPermissionData userPermissionData = BeanUtils.convert(request, UserPermissionData.class);
			userPermissionData.setCreatedAt(new Date());
			userPermissionData.setUpdatedAt(new Date());
			return this.userPermissionMapperEx.insert(userPermissionData)>0;
		}
		return false;
	}

	@Override
	public boolean update(UserPermissionRequest request){
		if( null != request ){
			UserPermissionData userPermissionData = BeanUtils.convert(request, UserPermissionData.class);
			userPermissionData.setUpdatedAt(new Date());
			return this.userPermissionMapperEx.updateByPrimaryKey(userPermissionData)>0;
		}
		return false;
	}

	@Override
	public UserPermissionResponse detail(Long id){
		UserPermissionData userPermissionData = this.userPermissionMapperEx.selectByPrimaryKey( id );
		if( null == userPermissionData ){
			return null;
		}
		return BeanUtils.convert(userPermissionData,UserPermissionResponse.class);
	}

	@Override
	public boolean delete(Long id){
		return this.userPermissionMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<UserPermissionResponse> listByCondition(UserPermissionRequest request,int page, int pageSize){
		BasePageResponse<UserPermissionResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<UserPermissionResponse> listByConditionData(UserPermissionRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<UserPermissionData> list =  this.userPermissionMapperEx.listByCondition(request.getUserId(), request.getPermissionId(), start, pageSize);
		return BeanUtils.convert(list,UserPermissionResponse.class);
	}


	private Long countByConditionData(UserPermissionRequest request){
		return this.userPermissionMapperEx.countByCondition(request.getUserId(), request.getPermissionId());
	}
}
