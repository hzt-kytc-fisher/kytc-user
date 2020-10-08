package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.UserRoleData;

public interface UserRoleMapper {

	int deleteByPrimaryKey(Long id);

	int insert(UserRoleData record);

	int insertSelective(UserRoleData record);

	UserRoleData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserRoleData record);

	int updateByPrimaryKey(UserRoleData record);
}
