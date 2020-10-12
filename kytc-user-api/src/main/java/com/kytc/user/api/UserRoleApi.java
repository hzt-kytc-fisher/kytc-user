package com.kytc.user.api;

import com.kytc.user.request.UserRoleRequest;
import com.kytc.user.request.UserRoleSearchRequest;
import com.kytc.user.response.UserRoleResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "用户角色操作")
@RequestMapping("/user/role")
public interface UserRoleApi {

	@ApiOperation("查询用户角色列表")
	@PostMapping("/infos")
	BasePageResponse<UserRoleResponse> listByCondition(
            @RequestBody UserRoleSearchRequest request);

	@ApiOperation("添加用户角色数据")
	@PostMapping("/info")
	Long add(@RequestBody @Valid UserRoleRequest request);

	@ApiOperation("修改用户角色数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid UserRoleRequest request);

	@ApiOperation("删除用户角色数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询用户角色详情")
	@GetMapping("/{id}")
	UserRoleResponse detail(@PathVariable("id") Long id);

	@ApiOperation("根据唯一索引删除用户角色数据")
	@DeleteMapping("/info")
	boolean delete(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId);
}
