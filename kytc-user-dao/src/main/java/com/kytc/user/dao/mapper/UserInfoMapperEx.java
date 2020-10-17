package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.UserInfoData;

import java.util.Date;
import java.util.List;

public interface UserInfoMapperEx extends UserInfoMapper {

	Integer updateEnabled(Long userId,boolean enable);

	List<UserInfoData> listByCondition(Long id, String username, String nickName, String idCard, Boolean enabled, String mobile, Integer sex, Long deptId, Date registerTime, int start, int limit);

	Long countByCondition(Long id, String username, String nickName, String idCard, Boolean enabled, String mobile, Integer sex, Long deptId, Date registerTime);

	Integer deleteByIdCard( String idCard);

	UserInfoData getByIdCard( String idCard);

	Integer deleteByUsername( String username);

	UserInfoData getByUsername( String username);

	Integer deleteByMobile( String mobile);

	UserInfoData getByMobile( String mobile);
}
