package com.kytc.user.response;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("部门信息 response")
public class DepartmentResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("上级部门")
	private Long parentId;
	@ApiModelProperty("部门key")
	private String deptKey;
	@ApiModelProperty("部门名称")
	private String deptName;
	@ApiModelProperty("地址")
	private String address;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("层级")
	private String level;
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
