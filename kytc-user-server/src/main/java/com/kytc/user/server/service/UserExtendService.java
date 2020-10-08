package com.kytc.user.server.service;

import com.kytc.user.request.UserExtendRequest;
import com.kytc.user.response.UserExtendResponse;
import com.kytc.framework.web.common.BasePageResponse;


public interface UserExtendService {

	boolean add(UserExtendRequest request);

	boolean update(UserExtendRequest request);

	UserExtendResponse detail(Long id);

	boolean delete(Long id);

	BasePageResponse<UserExtendResponse> listByCondition(
		UserExtendRequest request,
		int page,
		int pageSize);
}
