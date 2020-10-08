package com.kytc.user.server.api.impl;

import com.kytc.user.request.UserExtendRequest;
import com.kytc.user.response.UserExtendResponse;
import com.kytc.user.api.UserExtendApi;
import com.kytc.user.server.service.UserExtendService;
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
public class UserExtendApiImpl implements UserExtendApi {
	private final UserExtendService userExtendService;

	@Override
	public BasePageResponse<UserExtendResponse> listByCondition(
		@RequestBody @Valid UserExtendRequest request,
		@RequestParam("index")int page,
		@RequestParam("pageSize")int pageSize){
			return this.userExtendService.listByCondition( request,page, pageSize);
	}

	@Override
	public boolean add(@RequestBody @Valid UserExtendRequest request) {
		return this.userExtendService.add(request);
	}

	@Override
	public boolean update(@RequestBody @Valid UserExtendRequest request) {
		return this.userExtendService.update(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.userExtendService.delete(id);
	}

	@Override
	public UserExtendResponse detail(@PathVariable("id") Long id) {
		return this.userExtendService.detail(id);
	}
}
