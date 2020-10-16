package com.kytc.user.server.service;

import com.kytc.user.request.LoginRequest;
import com.kytc.user.request.UserLoginRequest;
import com.kytc.user.request.UserLoginSearchRequest;
import com.kytc.user.response.UserLoginResponse;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.user.response.UserResponse;

import java.util.List;


public interface UserLoginService {

	Long add(UserLoginRequest request);

	boolean update(UserLoginRequest request);

	UserLoginResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<UserLoginResponse> listByCondition(
			UserLoginSearchRequest request);

	UserResponse login(LoginRequest request);

	List<UserLoginResponse> selectByUserId(Long userId);
}
