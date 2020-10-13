package com.kytc.user.server.api.impl;

import com.kytc.user.request.LoginRequest;
import com.kytc.user.request.UserLoginRequest;
import com.kytc.user.request.UserLoginSearchRequest;
import com.kytc.user.response.UserLoginResponse;
import com.kytc.user.api.UserLoginApi;
import com.kytc.user.response.UserResponse;
import com.kytc.user.server.service.UserLoginService;
import com.kytc.framework.web.common.BasePageResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public class UserLoginApiImpl implements UserLoginApi {
	private final UserLoginService userLoginService;

	@Override
	public BasePageResponse<UserLoginResponse> listByCondition(
		@RequestBody @Valid UserLoginSearchRequest request){
			return this.userLoginService.listByCondition( request );
	}

	@Override
	public boolean add(@RequestBody @Valid UserLoginRequest request) {
		return this.userLoginService.add(request);
	}

	@Override
	public boolean update(@RequestBody @Valid UserLoginRequest request) {
		return this.userLoginService.update(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.userLoginService.delete(id);
	}

	@Override
	public UserLoginResponse detail(@PathVariable("id") Long id) {
		return this.userLoginService.detail(id);
	}

	@Override
	@RequestMapping
	public UserResponse login(@Valid LoginRequest request) {
		log.info("user login,loginKey:{},loginType:{}",request.getLoginKey(),request.getLoginTypeEnum());
		return this.userLoginService.login(request);
	}
}
