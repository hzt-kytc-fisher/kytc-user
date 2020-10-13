package com.kytc.user.api;

import com.kytc.user.request.UserInfoRequest;
import com.kytc.user.request.UserInfoSearchRequest;
import com.kytc.user.response.UserInfoResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "用户基本信息操作")
@RequestMapping("/user")
public interface UserInfoApi {

	@ApiOperation("查询用户基本信息列表")
	@PostMapping("/infos")
	BasePageResponse<UserInfoResponse> listByCondition(
            @RequestBody UserInfoSearchRequest request);

	@ApiOperation("添加用户基本信息数据")
	@PostMapping("/info")
	Long add(@RequestBody @Valid UserInfoRequest request);

	@ApiOperation("修改用户基本信息数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid UserInfoRequest request);

	@ApiOperation("删除用户基本信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询用户基本信息详情")
	@GetMapping("/{id}")
	UserInfoResponse detail(@PathVariable("id") Long id);
}
