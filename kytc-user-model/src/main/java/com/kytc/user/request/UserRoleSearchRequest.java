package com.kytc.user.request;

import lombok.Data;

import com.kytc.framework.web.common.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@Data
@ApiModel("用户角色查询 request")
public class UserRoleSearchRequest extends BasePageRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("用户ID")
	private Long userId;
	@ApiModelProperty("角色ID")
	private Long roleId;
}
