package com.kytc.user.request;

import java.io.Serializable;
import java.util.Date;

import com.kytc.framework.web.common.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("权限信息 request")
public class PermissionSearchRequest extends BasePageRequest implements Serializable {
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
}
