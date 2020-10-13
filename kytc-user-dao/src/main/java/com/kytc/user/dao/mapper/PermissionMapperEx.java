package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.PermissionData;

import java.util.List;

public interface PermissionMapperEx extends PermissionMapper {

	List<PermissionData> listByCondition(Long parentId, String permissionKey, String permissionName, String url, String level, String description, int start, int limit);

	Long countByCondition(Long parentId, String permissionKey, String permissionName, String url, String level, String description);
}
