package com.kytc.user.dao.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//权限信息
@Data
public class PermissionData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//主键
	private Long parentId;//上级ID
	private String permissionKey;//权限key
	private String permissionName;//权限名称
	private String url;//权限地址
	private String level;//层级
	private String description;//权限描述
	private Boolean idDeleted;//是否删除 1是0否
	private String createdBy;//创建人员
	private Date createdAt;//创建日期
	private String updatedBy;//修改人员
	private Date updatedAt;//修改日期
	private Date lastUpdatedAt;//最后更新时间
}
