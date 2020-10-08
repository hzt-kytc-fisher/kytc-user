package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.UserInfoData;

public interface UserInfoMapper {

	int deleteByPrimaryKey(Long id);

	int insert(UserInfoData record);

	int insertSelective(UserInfoData record);

	UserInfoData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserInfoData record);

	int updateByPrimaryKey(UserInfoData record);
}
