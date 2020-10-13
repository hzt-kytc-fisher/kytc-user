package com.kytc.user.server.service;

import com.kytc.user.request.RoleRequest;
import com.kytc.user.request.RoleSearchRequest;
import com.kytc.user.response.RoleResponse;
import com.kytc.framework.web.common.BasePageResponse;

import java.util.List;


public interface RoleService {

	Long add(RoleRequest request);

	boolean update(RoleRequest request);

	RoleResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<RoleResponse> listByCondition(
			RoleSearchRequest request);

	List<RoleResponse> selectByLevel(String level);
}
