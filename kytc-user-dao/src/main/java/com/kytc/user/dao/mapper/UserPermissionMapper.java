package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.UserPermissionData;

public interface UserPermissionMapper {

	int deleteByPrimaryKey(Long id);

	int insert(UserPermissionData record);

	int insertSelective(UserPermissionData record);

	UserPermissionData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserPermissionData record);

	int updateByPrimaryKey(UserPermissionData record);
}
