package com.kytc.user.server.api.impl;

import com.kytc.user.request.UserInfoRequest;
import com.kytc.user.request.UserInfoSearchRequest;
import com.kytc.user.response.UserInfoResponse;
import com.kytc.user.api.UserInfoApi;
import com.kytc.user.server.service.UserInfoService;
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
public class UserInfoApiImpl implements UserInfoApi {
	private final UserInfoService userInfoService;

	@Override
	public BasePageResponse<UserInfoResponse> listByCondition(
		@RequestBody @Valid UserInfoSearchRequest request){
			return this.userInfoService.listByCondition( request );
	}

	@Override
	public Long add(@RequestBody @Valid UserInfoRequest request) {
		return this.userInfoService.add(request);
	}

	@Override
	public boolean update(@RequestBody @Valid UserInfoRequest request) {
		return this.userInfoService.update(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.userInfoService.delete(id);
	}

	@Override
	public UserInfoResponse detail(@PathVariable("id") Long id) {
		return this.userInfoService.detail(id);
	}
}
