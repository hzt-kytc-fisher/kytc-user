package com.kytc.user.server.service;

import com.kytc.framework.cache.aop.RedisCache;
import com.kytc.user.request.UserInfoRequest;
import com.kytc.user.request.UserInfoSearchRequest;
import com.kytc.user.response.UserInfoResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface UserInfoService {

	Long add(UserInfoRequest request);

	boolean update(UserInfoRequest request);

	UserInfoResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<UserInfoResponse> listByCondition(
			UserInfoSearchRequest request);
}
