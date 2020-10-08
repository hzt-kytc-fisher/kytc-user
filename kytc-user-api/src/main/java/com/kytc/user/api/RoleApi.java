package com.kytc.user.api;

import com.kytc.user.request.RoleRequest;
import com.kytc.user.response.RoleResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "角色信息操作")
@RequestMapping("/role")
public interface RoleApi {

	@ApiOperation("查询角色信息列表")
	@PostMapping("/infos")
	BasePageResponse<RoleResponse> listByCondition(
            @RequestBody RoleRequest request,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize);

	@ApiOperation("添加角色信息数据")
	@PostMapping("/info")
	boolean add(@RequestBody @Valid RoleRequest request);

	@ApiOperation("修改角色信息数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid RoleRequest request);

	@ApiOperation("删除角色信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询角色信息详情")
	@GetMapping("/{id}")
	RoleResponse detail(@PathVariable("id") Long id);
}
