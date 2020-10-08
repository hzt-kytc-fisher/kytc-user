package com.kytc.user.request;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户扩展信息 request")
public class UserExtendRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("")
	private Long id;
	@ApiModelProperty("用户ID")
	private Long userId;
	@ApiModelProperty("部门ID")
	private Long deptId;
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
