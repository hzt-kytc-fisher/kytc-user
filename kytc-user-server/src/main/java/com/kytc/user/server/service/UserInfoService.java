package com.kytc.user.server.service;

import com.kytc.user.request.UserInfoRequest;
import com.kytc.user.response.UserInfoResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface UserInfoService {

	boolean add(UserInfoRequest request);

	boolean update(UserInfoRequest request);

	UserInfoResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<UserInfoResponse> listByCondition(
		UserInfoRequest request,
		int page,
		int pageSize);
}
