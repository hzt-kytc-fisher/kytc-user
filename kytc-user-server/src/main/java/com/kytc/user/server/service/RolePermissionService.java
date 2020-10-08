package com.kytc.user.server.service;

import com.kytc.user.request.RolePermissionRequest;
import com.kytc.user.response.RolePermissionResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface RolePermissionService {

	boolean add(RolePermissionRequest request);

	boolean update(RolePermissionRequest request);

	RolePermissionResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<RolePermissionResponse> listByCondition(
		RolePermissionRequest request,
		int page,
		int pageSize);
}
