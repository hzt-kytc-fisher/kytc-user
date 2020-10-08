package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.UserLoginData;

import java.util.List;

public interface UserLoginMapperEx extends UserLoginMapper {

	List<UserLoginData> listByCondition(String loginType, String loginKey, String loginPassword, Long userId, Boolean isDeleted, String salt, int start, int limit);

	Long countByCondition(String loginType, String loginKey, String loginPassword, Long userId, Boolean isDeleted, String salt);
}
