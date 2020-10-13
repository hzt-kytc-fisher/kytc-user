package com.kytc.user.api;

import com.kytc.user.request.LoginRequest;
import com.kytc.user.request.UserLoginRequest;
import com.kytc.user.request.UserLoginSearchRequest;
import com.kytc.user.response.UserLoginResponse;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.user.response.UserResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "用户登录信息操作")
@RequestMapping("/user/login")
public interface UserLoginApi {

	@ApiOperation("查询用户登录信息列表")
	@PostMapping("/infos")
	BasePageResponse<UserLoginResponse> listByCondition(
            @RequestBody UserLoginSearchRequest request);

	@ApiOperation("添加用户登录信息数据")
	@PostMapping("/info")
	boolean add(@RequestBody @Valid UserLoginRequest request);

	@ApiOperation("修改用户登录信息数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid UserLoginRequest request);

	@ApiOperation("删除用户登录信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询用户登录信息详情")
	@GetMapping("/{id}")
	UserLoginResponse detail(@PathVariable("id") Long id);

	@ApiOperation("登录")
	@PostMapping("")
	UserResponse login(@RequestBody @Valid LoginRequest request);
}
