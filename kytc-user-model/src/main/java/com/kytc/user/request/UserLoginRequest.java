package com.kytc.user.request;

import com.kytc.framework.web.common.BasePageRequest;
import com.kytc.user.enums.LoginTypeEnum;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户登录信息 request")
public class UserLoginRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@ApiModelProperty("登录类型 MOBILE MOBILE_PASSWORD USERNAME_PASSWORD EMAIL_PASSWORD QQ WECHAT WEIBO ENTERPRISE_WECHAT")
	private LoginTypeEnum loginType;
	@ApiModelProperty("登录key")
	private String loginKey;
	@ApiModelProperty("密码")
	private String loginPassword;
	@ApiModelProperty("用户ID")
	private Long userId;
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
