package com.kytc.user.api;

import com.kytc.user.request.PermissionRequest;
import com.kytc.user.response.PermissionResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "权限信息操作")
@RequestMapping("/permission")
public interface PermissionApi {

	@ApiOperation("查询权限信息列表")
	@PostMapping("/infos")
	BasePageResponse<PermissionResponse> listByCondition(
            @RequestBody PermissionRequest request,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize);

	@ApiOperation("添加权限信息数据")
	@PostMapping("/info")
	boolean add(@RequestBody @Valid PermissionRequest request);

	@ApiOperation("修改权限信息数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid PermissionRequest request);

	@ApiOperation("删除权限信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询权限信息详情")
	@GetMapping("/{id}")
	PermissionResponse detail(@PathVariable("id") Long id);
}
