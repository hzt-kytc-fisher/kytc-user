package com.kytc.user.server.impl;

import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.dao.data.PermissionData;
import com.kytc.user.request.RolePermissionSearchRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.server.service.RolePermissionService;
import com.kytc.user.request.RolePermissionRequest;
import com.kytc.user.response.RolePermissionResponse;
import com.kytc.user.dao.data.RolePermissionData;
import com.kytc.user.dao.mapper.RolePermissionMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class RolePermissionServiceImpl implements RolePermissionService {
	private final RolePermissionMapperEx rolePermissionMapperEx;

	@Override
	public Long add(RolePermissionRequest request){
		RolePermissionData data = this.rolePermissionMapperEx.getByRoleIdAndPermissionId(request.getRoleId(),request.getPermissionId());
		if( null != data ){
			return data.getId();
		}
		RolePermissionData rolePermissionData = BeanUtils.convert(request, RolePermissionData.class);
		rolePermissionData.setCreatedAt(new Date());
		rolePermissionData.setUpdatedAt(new Date());
		this.rolePermissionMapperEx.insert(rolePermissionData);
		return rolePermissionData.getId();
	}

	@Override
	public boolean delete(Long id){
		return this.rolePermissionMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<RolePermissionResponse> listByCondition(RolePermissionSearchRequest request){
		BasePageResponse<RolePermissionResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	@Override
	public List<PermissionResponse> selectByRoleId(Long roleId) {
		List<PermissionData> list = this.rolePermissionMapperEx.selectByRoleId(roleId);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return BeanUtils.convert(list,PermissionResponse.class);
	}

	@Override
	public List<PermissionResponse> selectByRoleIds(List<Long> roleIds) {
		List<PermissionData> list = this.rolePermissionMapperEx.selectByRoleIds(roleIds);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return BeanUtils.convert(list,PermissionResponse.class);
	}

	@Override
	public long countByPermissionId(Long permissionId) {
		RolePermissionSearchRequest request = new RolePermissionSearchRequest();
		request.setPermissionId(permissionId);
		return this.countByConditionData(request);
	}

	private List<RolePermissionResponse> listByConditionData(RolePermissionSearchRequest request){
		request.init();
		List<RolePermissionData> list =  this.rolePermissionMapperEx.listByCondition(request.getRoleId(), request.getPermissionId(),
				request.getStart(), request.getLimit());
		return BeanUtils.convert(list,RolePermissionResponse.class);
	}


	private Long countByConditionData(RolePermissionSearchRequest request){
		return this.rolePermissionMapperEx.countByCondition(request.getRoleId(), request.getPermissionId());
	}
}
