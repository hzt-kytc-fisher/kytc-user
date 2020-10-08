package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.RolePermissionService;
import com.kytc.user.request.RolePermissionRequest;
import com.kytc.user.response.RolePermissionResponse;
import com.kytc.user.dao.data.RolePermissionData;
import com.kytc.user.dao.mapper.RolePermissionMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class RolePermissionServiceImpl implements RolePermissionService {
	private final RolePermissionMapperEx rolePermissionMapperEx;

	@Override
	public boolean add(RolePermissionRequest request){
		if( null != request ){
			RolePermissionData rolePermissionData = BeanUtils.convert(request, RolePermissionData.class);
			rolePermissionData.setCreatedAt(new Date());
			rolePermissionData.setUpdatedAt(new Date());
			return this.rolePermissionMapperEx.insert(rolePermissionData)>0;
		}
		return false;
	}

	@Override
	public boolean update(RolePermissionRequest request){
		if( null != request ){
			RolePermissionData rolePermissionData = BeanUtils.convert(request, RolePermissionData.class);
			rolePermissionData.setUpdatedAt(new Date());
			return this.rolePermissionMapperEx.updateByPrimaryKey(rolePermissionData)>0;
		}
		return false;
	}

	@Override
	public RolePermissionResponse detail(Long id){
		RolePermissionData rolePermissionData = this.rolePermissionMapperEx.selectByPrimaryKey( id );
		if( null == rolePermissionData ){
			return null;
		}
		return BeanUtils.convert(rolePermissionData,RolePermissionResponse.class);
	}

	@Override
	public boolean delete(Long id){
		return this.rolePermissionMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<RolePermissionResponse> listByCondition(RolePermissionRequest request,int page, int pageSize){
		BasePageResponse<RolePermissionResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<RolePermissionResponse> listByConditionData(RolePermissionRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<RolePermissionData> list =  this.rolePermissionMapperEx.listByCondition(request.getRoleId(), request.getPermissionId(), start, pageSize);
		return BeanUtils.convert(list,RolePermissionResponse.class);
	}


	private Long countByConditionData(RolePermissionRequest request){
		return this.rolePermissionMapperEx.countByCondition(request.getRoleId(), request.getPermissionId());
	}
}
