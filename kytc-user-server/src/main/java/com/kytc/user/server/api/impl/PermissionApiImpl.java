package com.kytc.user.server.api.impl;

import com.kytc.user.request.PermissionRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.api.PermissionApi;
import com.kytc.user.server.service.PermissionService;
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
public class PermissionApiImpl implements PermissionApi {
	private final PermissionService permissionService;

	@Override
	public BasePageResponse<PermissionResponse> listByCondition(
		@RequestBody @Valid PermissionRequest request,
		@RequestParam("index")int page,
		@RequestParam("pageSize")int pageSize){
			return this.permissionService.listByCondition( request,page, pageSize);
	}

	@Override
	public boolean add(@RequestBody @Valid PermissionRequest request) {
		return this.permissionService.add(request);
	}

	@Override
	public boolean update(@RequestBody @Valid PermissionRequest request) {
		return this.permissionService.update(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.permissionService.delete(id);
	}

	@Override
	public PermissionResponse detail(@PathVariable("id") Long id) {
		return this.permissionService.detail(id);
	}
}
