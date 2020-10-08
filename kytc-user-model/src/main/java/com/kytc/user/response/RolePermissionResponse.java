package com.kytc.user.response;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("角色权限信息 response")
public class RolePermissionResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("角色ID")
	private Long roleId;
	@ApiModelProperty("权限ID")
	private Long permissionId;
	@ApiModelProperty("创建人员")
	private String createdBy;
	@ApiModelProperty("创建日期")
	private Date createdAt;
	@ApiModelProperty("修改人员")
	private String updatedBy;
	@ApiModelProperty("修改日期")
	private Date updatedAt;
	@ApiModelProperty("最后更新时间")
	private Date lastUpdatedAt;
}
