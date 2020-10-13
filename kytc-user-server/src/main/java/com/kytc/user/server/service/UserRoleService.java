package com.kytc.user.server.service;

import com.kytc.user.request.UserRoleRequest;
import com.kytc.user.request.UserRoleSearchRequest;
import com.kytc.user.response.RoleResponse;
import com.kytc.user.response.UserRoleResponse;
import com.kytc.framework.web.common.BasePageResponse;

import java.util.List;


public interface UserRoleService {

	BasePageResponse<UserRoleResponse> listByCondition(
            UserRoleSearchRequest request);

	Long add(UserRoleRequest request);

	boolean delete(Long id);

	boolean delete(Long userId, Long roleId);

	List<RoleResponse> selectByUserId(Long userId);
}
