package com.kytc.user.server.service;

import com.kytc.user.request.PermissionRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface PermissionService {

	boolean add(PermissionRequest request);

	boolean update(PermissionRequest request);

	PermissionResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<PermissionResponse> listByCondition(
		PermissionRequest request,
		int page,
		int pageSize);
}
