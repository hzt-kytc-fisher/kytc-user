package com.kytc.user.api;

import com.kytc.user.request.UserRoleRequest;
import com.kytc.user.response.UserRoleResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "用户角色信息操作")
@RequestMapping("/user/role")
public interface UserRoleApi {

	@ApiOperation("查询用户角色信息列表")
	@PostMapping("/infos")
	BasePageResponse<UserRoleResponse> listByCondition(
            @RequestBody UserRoleRequest request,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize);

	@ApiOperation("添加用户角色信息数据")
	@PostMapping("/info")
	boolean add(@RequestBody @Valid UserRoleRequest request);

	@ApiOperation("修改用户角色信息数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid UserRoleRequest request);

	@ApiOperation("删除用户角色信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询用户角色信息详情")
	@GetMapping("/{id}")
	UserRoleResponse detail(@PathVariable("id") Long id);
}
