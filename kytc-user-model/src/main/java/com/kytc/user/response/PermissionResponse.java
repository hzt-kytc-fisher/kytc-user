package com.kytc.user.response;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("权限信息 response")
public class PermissionResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("上级ID")
	private Long parentId;
	@ApiModelProperty("权限key")
	private String permissionKey;
	@ApiModelProperty("权限名称")
	private String permissionName;
	@ApiModelProperty("权限地址")
	private String url;
	@ApiModelProperty("层级")
	private String level;
	@ApiModelProperty("权限描述")
	private String description;
	@ApiModelProperty("是否删除 1是0否")
	private Boolean idDeleted;
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
