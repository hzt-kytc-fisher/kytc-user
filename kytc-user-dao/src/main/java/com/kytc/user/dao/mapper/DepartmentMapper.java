package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.DepartmentData;

public interface DepartmentMapper {

	int deleteByPrimaryKey(Long id);

	int insert(DepartmentData record);

	int insertSelective(DepartmentData record);

	DepartmentData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(DepartmentData record);

	int updateByPrimaryKey(DepartmentData record);
}
