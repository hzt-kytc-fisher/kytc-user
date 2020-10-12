package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.request.UserPermissionSearchRequest;
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
		if( null == request ){
			return false;
		}
		UserPermissionData data = this.userPermissionMapperEx.getByUserIdAndPermissionId(request.getUserId(),request.getPermissionId());
		if( null != data ){
			return true;
		}
		UserPermissionData userPermissionData = BeanUtils.convert(request, UserPermissionData.class);
		userPermissionData.setCreatedAt(new Date());
		userPermissionData.setUpdatedAt(new Date());
		return this.userPermissionMapperEx.insert(userPermissionData)>0;
	}

	@Override
	public boolean delete(Long id){
		return this.userPermissionMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<UserPermissionResponse> listByCondition(UserPermissionSearchRequest request){
		BasePageResponse<UserPermissionResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	private List<UserPermissionResponse> listByConditionData(UserPermissionSearchRequest request){
		request.init();
		List<UserPermissionData> list =  this.userPermissionMapperEx.listByCondition(request.getUserId(), request.getPermissionId(),request.getStart(),request.getLimit());
		return BeanUtils.convert(list,UserPermissionResponse.class);
	}


	private Long countByConditionData(UserPermissionSearchRequest request){
		return this.userPermissionMapperEx.countByCondition(request.getUserId(), request.getPermissionId());
	}
}
