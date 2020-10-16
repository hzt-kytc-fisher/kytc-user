package com.kytc.user.server.service;

import com.kytc.user.request.UserPermissionRequest;
import com.kytc.user.request.UserPermissionSearchRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.response.UserPermissionResponse;
import com.kytc.framework.web.common.BasePageResponse;

import java.util.List;


public interface UserPermissionService {

	boolean add(UserPermissionRequest request);

	boolean delete(Long id);

	BasePageResponse<UserPermissionResponse> listByCondition(
		UserPermissionSearchRequest request);

	List<PermissionResponse> selectByUserId(Long userId);

	List<PermissionResponse> selectByUserIdAll(Long userId);

	long countByPermissionId(Long permissionId);

	boolean delete(Long userId,Long permissionId);
}
