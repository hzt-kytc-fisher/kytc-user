package com.kytc.user.response;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户登录信息 response")
public class UserLoginResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("登录类型 PHONE USERNAME QQ WX WB")
	private String loginType;
	@ApiModelProperty("登录key")
	private String loginKey;
	@ApiModelProperty("用户ID")
	private Long userId;
	@ApiModelProperty("是否删除 1是 0否")
	private Boolean isDeleted;
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
	private Boolean isCurrentLogin;
}
