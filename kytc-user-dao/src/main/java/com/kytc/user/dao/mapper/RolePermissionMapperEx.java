package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.PermissionData;
import com.kytc.user.dao.data.RolePermissionData;

import java.util.List;

public interface RolePermissionMapperEx extends RolePermissionMapper {

	List<RolePermissionData> listByCondition(Long roleId, Long permissionId, int start, int limit);

	Long countByCondition(Long roleId, Long permissionId);

	List<PermissionData> selectByRoleId(Long roleId);

	List<PermissionData> selectByRoleIds(List<Long> list);
}
