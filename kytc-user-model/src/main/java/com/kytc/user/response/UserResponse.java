package com.kytc.user.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户登录获取信息 response")
public class UserResponse extends UserInfoResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String token;
	private List<RoleResponse> roles;
	private DepartmentResponse departmentResponse;
	private List<PermissionResponse> permissionResponses;
	private List<String> permissions;
	private UserExtendResponse userExtendResponse;
	private List<UserLoginResponse> userLoginResponses;
}
