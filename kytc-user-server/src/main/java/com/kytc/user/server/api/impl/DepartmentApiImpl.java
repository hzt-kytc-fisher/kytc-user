package com.kytc.user.server.api.impl;

import com.kytc.user.request.DepartmentRequest;
import com.kytc.user.request.DepartmentSearchRequest;
import com.kytc.user.response.DepartmentResponse;
import com.kytc.user.api.DepartmentApi;
import com.kytc.user.server.service.DepartmentService;
import com.kytc.framework.web.common.BasePageResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class DepartmentApiImpl implements DepartmentApi {
	private final DepartmentService departmentService;

	@Override
	public BasePageResponse<DepartmentResponse> listByCondition(@RequestBody @Valid DepartmentSearchRequest request){
			return this.departmentService.listByCondition( request );
	}

	@Override
	public Long add(@RequestBody @Valid DepartmentRequest request) {
		return this.departmentService.add(request);
	}

	@Override
	public boolean update(@RequestBody @Valid DepartmentRequest request) {
		return this.departmentService.update(request);
	}

	@Override
	public boolean delete(@PathVariable("id") Long id) {
		return this.departmentService.delete(id);
	}

	@Override
	public DepartmentResponse detail(@PathVariable("id") Long id) {
		return this.departmentService.detail(id);
	}
}
