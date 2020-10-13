package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.PermissionData;
import com.kytc.user.dao.data.RoleData;
import com.kytc.user.dao.data.UserPermissionData;

import java.util.List;

public interface UserPermissionMapperEx extends UserPermissionMapper {

	List<UserPermissionData> listByCondition(Long userId, Long permissionId, int start, int limit);

	Long countByCondition(Long userId, Long permissionId);

	UserPermissionData getByUserIdAndPermissionId(Long userId,Long permissionId);

	List<PermissionData> selectByUserId(Long userId);

}
