package com.kytc.user.server.service;

import com.kytc.user.request.DepartmentRequest;
import com.kytc.user.request.DepartmentSearchRequest;
import com.kytc.user.response.DepartmentResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface DepartmentService {

	boolean add(DepartmentRequest request);

	boolean update(DepartmentRequest request);

	DepartmentResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<DepartmentResponse> listByCondition(
			DepartmentSearchRequest request);
}
