package com.kytc.user.server.api.impl;

import com.kytc.user.request.RoleRequest;
import com.kytc.user.request.RoleSearchRequest;
import com.kytc.user.response.RoleResponse;
import com.kytc.user.api.RoleApi;
import com.kytc.user.server.service.RoleService;
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
public class RoleApiImpl implements RoleApi {
	private final RoleService roleService;

	@Override
	public BasePageResponse<RoleResponse> listByCondition(
		@RequestBody @Valid RoleSearchRequest request){
			return this.roleService.listByCondition( request );
	}

	@Override
	public Long add(@RequestBody @Valid RoleRequest request) {
		return this.roleService.add(request);
	}

	@Override
	public boolean update(@RequestBody @Valid RoleRequest request) {
		return this.roleService.update(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.roleService.delete(id);
	}

	@Override
	public RoleResponse detail(@PathVariable("id") Long id) {
		return this.roleService.detail(id);
	}
}
