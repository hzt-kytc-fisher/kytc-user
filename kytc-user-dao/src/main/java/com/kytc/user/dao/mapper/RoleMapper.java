package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.RoleData;

public interface RoleMapper {

	int deleteByPrimaryKey(Long id);

	int insert(RoleData record);

	int insertSelective(RoleData record);

	RoleData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(RoleData record);

	int updateByPrimaryKey(RoleData record);
}
