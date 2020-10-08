package com.kytc.user.api;

import com.kytc.user.request.DepartmentRequest;
import com.kytc.user.response.DepartmentResponse;

import com.kytc.framework.web.common.BasePageResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@Api(tags = "部门信息操作")
@RequestMapping("/department")
public interface DepartmentApi {

	@ApiOperation("查询部门信息列表")
	@PostMapping("/infos")
	BasePageResponse<DepartmentResponse> listByCondition(
            @RequestBody DepartmentRequest request,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize);

	@ApiOperation("添加部门信息数据")
	@PostMapping("/info")
	boolean add(@RequestBody @Valid DepartmentRequest request);

	@ApiOperation("修改部门信息数据")
	@PutMapping("/info")
	boolean update(@RequestBody @Valid DepartmentRequest request);

	@ApiOperation("删除部门信息数据")
	@DeleteMapping("/{id}")
	boolean delete(@PathVariable("id") Long id);

	@ApiOperation("查询部门信息详情")
	@GetMapping("/{id}")
	DepartmentResponse detail(@PathVariable("id") Long id);
}
