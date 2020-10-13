package com.kytc.user.request;

import java.io.Serializable;
import java.util.Date;

import com.kytc.framework.web.common.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户基本信息 request")
public class UserInfoSearchRequest extends BasePageRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("用户名")
	private String username;
	@ApiModelProperty("用户昵称")
	private String nickName;
	@ApiModelProperty("身份证号")
	private String idCard;
	@ApiModelProperty("是否启用")
	private Boolean enabled;
	@ApiModelProperty("手机号")
	private String mobile;
	@ApiModelProperty("注册时间")
	private Date registerTime;
}
