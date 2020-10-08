package com.kytc.user.server.service;

import com.kytc.user.request.UserLoginRequest;
import com.kytc.user.response.UserLoginResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface UserLoginService {

	boolean add(UserLoginRequest request);

	boolean update(UserLoginRequest request);

	UserLoginResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<UserLoginResponse> listByCondition(
		UserLoginRequest request,
		int page,
		int pageSize);
}
