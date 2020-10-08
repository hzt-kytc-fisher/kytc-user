package com.kytc.user.request;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("角色信息 request")
public class RoleRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("上级角色ID")
	private Long parentId;
	@ApiModelProperty("角色key")
	private String roleKey;
	@ApiModelProperty("角色名称")
	private String roleName;
	@ApiModelProperty("是否删除")
	private Boolean isDeleted;
	@ApiModelProperty("层级")
	private String level;
	@ApiModelProperty("描述")
	private String description;
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
