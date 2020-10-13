package com.kytc.user.server.api.impl;

import com.kytc.user.request.UserRoleRequest;
import com.kytc.user.request.UserRoleSearchRequest;
import com.kytc.user.response.RoleResponse;
import com.kytc.user.response.UserRoleResponse;
import com.kytc.user.api.UserRoleApi;
import com.kytc.user.server.service.UserRoleService;
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
public class UserRoleApiImpl implements UserRoleApi {
	private final UserRoleService userRoleService;

	@Override
	public BasePageResponse<UserRoleResponse> listByCondition(
		@RequestBody @Valid UserRoleSearchRequest request){
			return this.userRoleService.listByCondition( request );
	}

	@Override
	public Long add(@RequestBody @Valid UserRoleRequest request) {
		return this.userRoleService.add(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.userRoleService.delete(id);
	}

	@Override
	public List<RoleResponse> selectByUserId(@PathVariable("userId") Long userId) {
		return this.userRoleService.selectByUserId(userId);
	}

	@Override
	public boolean delete(@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId){
		return this.userRoleService.delete(userId,roleId);
	}
}
