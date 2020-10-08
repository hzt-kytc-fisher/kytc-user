package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.RoleData;

import java.util.List;

public interface RoleMapperEx extends RoleMapper {

	List<RoleData> listByCondition(Long parentId, String roleKey, String roleName, Boolean isDeleted, String level, String description, int start, int limit);

	Long countByCondition(Long parentId, String roleKey, String roleName, Boolean isDeleted, String level, String description);
}
