package com.kytc.user.dao.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户登录信息
@Data
public class UserLoginData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//主键
	private String loginType;//登录类型 PHONE USERNAME QQ WX WB
	private String loginKey;//登录key
	private String loginPassword;//密码
	private Long userId;//用户ID
	private Boolean isDeleted;//是否删除 1是 0否
	private String salt;//盐
	private String createdBy;//创建人员
	private Date createdAt;//创建日期
	private String updatedBy;//修改人员
	private Date updatedAt;//修改日期
	private Date lastUpdatedAt;//最后更新时间
}
