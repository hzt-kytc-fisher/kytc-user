package com.kytc.user.server.api.impl;

import com.kytc.user.request.RolePermissionRequest;
import com.kytc.user.response.RolePermissionResponse;
import com.kytc.user.api.RolePermissionApi;
import com.kytc.user.server.service.RolePermissionService;
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
public class RolePermissionApiImpl implements RolePermissionApi {
	private final RolePermissionService rolePermissionService;

	@Override
	public BasePageResponse<RolePermissionResponse> listByCondition(
		@RequestBody @Valid RolePermissionRequest request,
		@RequestParam("index")int page,
		@RequestParam("pageSize")int pageSize){
			return this.rolePermissionService.listByCondition( request,page, pageSize);
	}

	@Override
	public boolean add(@RequestBody @Valid RolePermissionRequest request) {
		return this.rolePermissionService.add(request);
	}

	@Override
	public boolean update(@RequestBody @Valid RolePermissionRequest request) {
		return this.rolePermissionService.update(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.rolePermissionService.delete(id);
	}

	@Override
	public RolePermissionResponse detail(@PathVariable("id") Long id) {
		return this.rolePermissionService.detail(id);
	}
}
