package com.kytc.user.server.api.impl;

import com.kytc.user.request.UserPermissionRequest;
import com.kytc.user.request.UserPermissionSearchRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.response.UserPermissionResponse;
import com.kytc.user.api.UserPermissionApi;
import com.kytc.user.server.service.UserPermissionService;
import com.kytc.framework.web.common.BasePageResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserPermissionApiImpl implements UserPermissionApi {
	private final UserPermissionService userPermissionService;

	@Override
	public BasePageResponse<UserPermissionResponse> listByCondition(
		@RequestBody @Valid UserPermissionSearchRequest request){
			return this.userPermissionService.listByCondition( request );
	}

	@Override
	public Long add(@RequestBody @Valid UserPermissionRequest request) {
		return this.userPermissionService.add(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.userPermissionService.delete(id);
	}

	@Override
	public List<PermissionResponse> selectByUserId(@PathVariable("userId")Long userId) {
		return null;
	}

	@Override
	public List<PermissionResponse> selectByUserIdAll(@PathVariable("userId")Long userId) {
		return null;
	}
}
