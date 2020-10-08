package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.UserLoginData;

public interface UserLoginMapper {

	int deleteByPrimaryKey(Long id);

	int insert(UserLoginData record);

	int insertSelective(UserLoginData record);

	UserLoginData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserLoginData record);

	int updateByPrimaryKey(UserLoginData record);
}
