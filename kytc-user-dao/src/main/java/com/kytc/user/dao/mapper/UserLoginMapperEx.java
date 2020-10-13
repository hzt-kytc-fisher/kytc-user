package com.kytc.user.dao.mapper;


import com.kytc.framework.database.DataSource;
import com.kytc.user.dao.data.UserLoginData;

import java.util.List;

/**
 * @author hezhitong
 */
public interface UserLoginMapperEx extends UserLoginMapper {

	/***
	 *
	 * 查询列表数据
	 *
	 * @Author: 何志同
	 * @Date: 2020/10/10 10:41
	 * @Description: 
	 * @param loginType
	 * @param loginKey
	 * @param userId
	 * @param start
	 * @param limit
	 * @return {@link List< UserLoginData>}
	**/
	List<UserLoginData> listByCondition(String loginType, String loginKey, Long userId, int start, int limit);

	Long countByCondition(String loginType, String loginKey, Long userId);

	@DataSource("master")
	UserLoginData getByLoginTypeAndKey(String loginType,String loginKey);

	List<UserLoginData> selectByUserId(Long userId);
}
