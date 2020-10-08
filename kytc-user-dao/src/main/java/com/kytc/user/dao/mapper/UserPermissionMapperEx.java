package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.UserPermissionData;

import java.util.List;

public interface UserPermissionMapperEx extends UserPermissionMapper {

	List<UserPermissionData> listByCondition(Long userId, Long permissionId, int start, int limit);

	Long countByCondition(Long userId, Long permissionId);
}
