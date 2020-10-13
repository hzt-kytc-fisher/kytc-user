package com.kytc.user.request;

import java.io.Serializable;
import java.util.Date;

import com.kytc.framework.web.common.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户扩展信息 查询request")
public class UserExtendSearchRequest extends BasePageRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("用户ID")
	private Long userId;
	@ApiModelProperty("部门ID")
	private Long deptId;
}
