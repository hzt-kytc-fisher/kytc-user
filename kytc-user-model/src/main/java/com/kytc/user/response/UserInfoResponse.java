package com.kytc.user.response;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户基本信息 response")
public class UserInfoResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("用户名")
	private String username;
	@ApiModelProperty("用户昵称")
	private String nickName;
	@ApiModelProperty("头像")
	private String headPicture;
	@ApiModelProperty("身份证号")
	private String idCard;
	@ApiModelProperty("是否启用")
	private Boolean enabled;
	@ApiModelProperty("手机号")
	private String mobile;
	@ApiModelProperty("性别 0未知性别 1男性 2女性 9未说明性别")
	private Integer sex;
	@ApiModelProperty("部门ID")
	private Long deptId;
	@ApiModelProperty("是否删除")
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
