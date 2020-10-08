package com.kytc.user.server.service;

import com.kytc.user.request.UserRoleRequest;
import com.kytc.user.response.UserRoleResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface UserRoleService {

	boolean add(UserRoleRequest request);

	boolean update(UserRoleRequest request);

	UserRoleResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<UserRoleResponse> listByCondition(
		UserRoleRequest request,
		int page,
		int pageSize);
}
