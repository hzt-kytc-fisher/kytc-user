package com.kytc.user.dao.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户基本信息
@Data
public class UserInfoData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//主键
	private String username;//用户名
	private String nickName;//用户昵称
	private String headPicture;//头像
	private String idCard;//身份证号
	private Boolean enabled;//是否启用
	private String mobile;//手机号
	private Boolean isDeleted;//是否删除
	private Date registerTime;//注册时间
	private String createdBy;//创建人员
	private Date createdAt;//创建日期
	private String updatedBy;//修改人员
	private Date updatedAt;//修改日期
	private Date lastUpdatedAt;//最后更新时间
}
