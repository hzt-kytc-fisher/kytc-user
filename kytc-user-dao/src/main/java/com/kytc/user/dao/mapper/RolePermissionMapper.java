package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.RolePermissionData;

public interface RolePermissionMapper {

	int deleteByPrimaryKey(Long id);

	int insert(RolePermissionData record);

	int insertSelective(RolePermissionData record);

	RolePermissionData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(RolePermissionData record);

	int updateByPrimaryKey(RolePermissionData record);
}
