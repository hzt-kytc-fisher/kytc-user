package com.kytc.user.api;

import com.kytc.user.request.UserPermissionRequest;
import com.kytc.user.response.UserPermissionResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "角色权限信息操作")
@RequestMapping("/user/permission")
public interface UserPermissionApi {

	@ApiOperation("查询角色权限信息列表")
	@PostMapping("/infos")
	BasePageResponse<UserPermissionResponse> listByCondition(
            @RequestBody UserPermissionRequest request,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize);

	@ApiOperation("添加角色权限信息数据")
	@PostMapping("/info")
	boolean add(@RequestBody @Valid UserPermissionRequest request);

	@ApiOperation("修改角色权限信息数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid UserPermissionRequest request);

	@ApiOperation("删除角色权限信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询角色权限信息详情")
	@GetMapping("/{id}")
	UserPermissionResponse detail(@PathVariable("id") Long id);
}
