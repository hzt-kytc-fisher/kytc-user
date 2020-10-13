package com.kytc.user.request;

import java.io.Serializable;
import java.util.Date;

import com.kytc.framework.web.common.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户登录信息 search request")
public class UserLoginSearchRequest extends BasePageRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("登录类型 PHONE USERNAME QQ WX WB")
	private String loginType;
	@ApiModelProperty("登录key")
	private String loginKey;
	@ApiModelProperty("用户ID")
	private Long userId;
}
