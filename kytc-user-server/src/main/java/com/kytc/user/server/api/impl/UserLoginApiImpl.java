package com.kytc.user.server.api.impl;

import com.kytc.user.request.UserLoginRequest;
import com.kytc.user.response.UserLoginResponse;
import com.kytc.user.api.UserLoginApi;
import com.kytc.user.server.service.UserLoginService;
import com.kytc.framework.web.common.BasePageResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserLoginApiImpl implements UserLoginApi {
	private final UserLoginService userLoginService;

	@Override
	public BasePageResponse<UserLoginResponse> listByCondition(
		@RequestBody @Valid UserLoginRequest request,
		@RequestParam("index")int page,
		@RequestParam("pageSize")int pageSize){
			return this.userLoginService.listByCondition( request,page, pageSize);
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
}
