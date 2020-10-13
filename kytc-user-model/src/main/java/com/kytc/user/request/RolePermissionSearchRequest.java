package com.kytc.user.request;

import java.io.Serializable;
import java.util.Date;

import com.kytc.framework.web.common.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("角色权限信息 查询 request")
public class RolePermissionSearchRequest extends BasePageRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("角色ID")
	private Long roleId;
	@ApiModelProperty("权限ID")
	private Long permissionId;
}
