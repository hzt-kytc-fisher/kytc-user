package com.kytc.user.dao.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//角色权限信息
@Data
public class RolePermissionData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//主键
	private Long roleId;//角色ID
	private Long permissionId;//权限ID
	private String createdBy;//创建人员
	private Date createdAt;//创建日期
	private String updatedBy;//修改人员
	private Date updatedAt;//修改日期
	private Date lastUpdatedAt;//最后更新时间
}
