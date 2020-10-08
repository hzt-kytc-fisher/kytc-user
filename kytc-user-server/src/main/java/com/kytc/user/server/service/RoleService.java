package com.kytc.user.server.service;

import com.kytc.user.request.RoleRequest;
import com.kytc.user.response.RoleResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface RoleService {

	boolean add(RoleRequest request);

	boolean update(RoleRequest request);

	RoleResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<RoleResponse> listByCondition(
		RoleRequest request,
		int page,
		int pageSize);
}
