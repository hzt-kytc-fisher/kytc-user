package com.kytc.user.request;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户基本信息 request")
public class UserInfoRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@NotNull(message="用户名不能为空")
	@ApiModelProperty("用户名")
	private String username;
	@NotNull(message = "用户昵称不能为空")
	@ApiModelProperty("用户昵称")
	private String nickName;
	@ApiModelProperty("头像")
	private String headPicture;
	@ApiModelProperty("身份证号")
	private String idCard;
	@NotNull(message = "是否启用不能为空")
	@ApiModelProperty("是否启用")
	private Boolean enabled;
	@ApiModelProperty("手机号")
	private String mobile;
	@NotNull(message = "性别不能为空")
	@ApiModelProperty("性别 0未知性别 1男性 2女性 9未说明性别")
	private Integer sex;
	@NotNull(message = "部门ID不能为空")
	@ApiModelProperty("部门ID")
	private Long deptId;
	private Boolean isDeleted;
	@ApiModelProperty("注册时间")
	private Date registerTime;
	@ApiModelProperty("创建人员")
	private String createdBy;
	@ApiModelProperty("创建日期")
	private Date createdAt;
	@ApiModelProperty("修改人员")
	private String updatedBy;
	@ApiModelProperty("修改日期")
	private Date updatedAt;
}
