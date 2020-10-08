package com.kytc.user.server.service;

import com.kytc.user.request.UserPermissionRequest;
import com.kytc.user.response.UserPermissionResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface UserPermissionService {

	boolean add(UserPermissionRequest request);

	boolean update(UserPermissionRequest request);

	UserPermissionResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<UserPermissionResponse> listByCondition(
		UserPermissionRequest request,
		int page,
		int pageSize);
}
