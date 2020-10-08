package com.kytc.user.api;

import com.kytc.user.request.UserExtendRequest;
import com.kytc.user.response.UserExtendResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "用户扩展信息操作")
@RequestMapping("/user/extend")
public interface UserExtendApi {

	@ApiOperation("查询用户扩展信息列表")
	@PostMapping("/infos")
	BasePageResponse<UserExtendResponse> listByCondition(
            @RequestBody UserExtendRequest request,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize);

	@ApiOperation("添加用户扩展信息数据")
	@PostMapping("/info")
	boolean add(@RequestBody @Valid UserExtendRequest request);

	@ApiOperation("修改用户扩展信息数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid UserExtendRequest request);

	@ApiOperation("删除用户扩展信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询用户扩展信息详情")
	@GetMapping("/{id}")
	UserExtendResponse detail(@PathVariable("id") Long id);
}
