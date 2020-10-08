package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.UserInfoData;

import java.util.Date;
import java.util.List;

public interface UserInfoMapperEx extends UserInfoMapper {

	List<UserInfoData> listByCondition(String username, String nickName, String headPicture, String idCard, Boolean enabled, String mobile, Boolean isDeleted, Date registerTime, int start, int limit);

	Long countByCondition(String username, String nickName, String headPicture, String idCard, Boolean enabled, String mobile, Boolean isDeleted, Date registerTime);
}