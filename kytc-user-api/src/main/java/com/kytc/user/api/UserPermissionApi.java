package com.kytc.user.api;

import com.kytc.user.request.UserPermissionRequest;
import com.kytc.user.request.UserPermissionSearchRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.response.UserPermissionResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "角色权限信息操作")
@RequestMapping("/user/permission")
public interface UserPermissionApi {

	@ApiOperation("查询角色权限信息列表")
	@PostMapping("/infos")
	BasePageResponse<UserPermissionResponse> listByCondition(
            @RequestBody UserPermissionSearchRequest request);

	@ApiOperation("添加角色权限信息数据")
	@PostMapping("/info")
	Long add(@RequestBody @Valid UserPermissionRequest request);

	@ApiOperation("删除角色权限信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询用户权限信息数据 -- 直接赋予的")
	@GetMapping("/{userId}")
	List<PermissionResponse> selectByUserId(@PathVariable("userId") Long userId);

	@ApiOperation("查询用户权限信息数据 -- 包含拥有角色对应的")
	@GetMapping("/{userId}/all")
	List<PermissionResponse> selectByUserIdAll(@PathVariable("userId") Long userId);
}
