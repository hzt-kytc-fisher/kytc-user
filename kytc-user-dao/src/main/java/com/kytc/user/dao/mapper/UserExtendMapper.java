package com.kytc.user.dao.mapper;

import com.kytc.user.dao.data.UserExtendData;

public interface UserExtendMapper {

	int deleteByPrimaryKey(Long id);

	int insert(UserExtendData record);

	int insertSelective(UserExtendData record);

	UserExtendData selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserExtendData record);

	int updateByPrimaryKey(UserExtendData record);
}
