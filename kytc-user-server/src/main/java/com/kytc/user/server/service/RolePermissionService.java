package com.kytc.user.server.service;

import com.kytc.user.request.RolePermissionRequest;
import com.kytc.user.request.RolePermissionSearchRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.response.RolePermissionResponse;
import com.kytc.framework.web.common.BasePageResponse;

import java.util.List;


public interface RolePermissionService {

	boolean add(RolePermissionRequest request);

	boolean delete(Long id);

	BasePageResponse<RolePermissionResponse> listByCondition(
			RolePermissionSearchRequest request);

	List<PermissionResponse> selectByRoleId(Long roleId);

	List<PermissionResponse> selectByRoleIds(List<Long> roleIds);

	long countByPermissionId(Long permissionId);
}
