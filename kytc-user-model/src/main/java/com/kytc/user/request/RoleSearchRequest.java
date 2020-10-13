package com.kytc.user.request;

import java.io.Serializable;
import java.util.Date;

import com.kytc.framework.web.common.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("角色信息 查询request")
public class RoleSearchRequest extends BasePageRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("上级角色ID")
	private Long parentId;
	@ApiModelProperty("角色key")
	private String roleKey;
	@ApiModelProperty("角色名称")
	private String roleName;
	@ApiModelProperty("层级")
	private String level;
	@ApiModelProperty("描述")
	private String description;
}
