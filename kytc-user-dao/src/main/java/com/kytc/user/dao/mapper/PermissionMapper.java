package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.PermissionData;

public interface PermissionMapper {

	int deleteByPrimaryKey(Long id);

	int insert(PermissionData record);

	int insertSelective(PermissionData record);

	PermissionData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(PermissionData record);

	int updateByPrimaryKey(PermissionData record);
}
