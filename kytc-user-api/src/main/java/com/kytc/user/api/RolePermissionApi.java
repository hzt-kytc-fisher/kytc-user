package com.kytc.user.api;

import com.kytc.user.request.RolePermissionRequest;
import com.kytc.user.request.RolePermissionSearchRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.response.RolePermissionResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "角色权限信息操作")
@RequestMapping("/role/permission")
public interface RolePermissionApi {

	@ApiOperation("查询角色权限信息列表")
	@PostMapping("/infos")
	BasePageResponse<RolePermissionResponse> listByCondition(
            @RequestBody RolePermissionSearchRequest request);

	@ApiOperation("添加角色权限信息数据")
	@PostMapping("/info")
	boolean add(@RequestBody @Valid RolePermissionRequest request);

	@ApiOperation("删除角色权限信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询角色权限信息详情")
	@GetMapping("/{roleId}")
	List<PermissionResponse> selectByRoleId(@PathVariable("roleId") Long roleId);
}
