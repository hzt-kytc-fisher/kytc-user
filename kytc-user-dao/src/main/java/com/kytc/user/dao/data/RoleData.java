package com.kytc.user.dao.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//角色信息
@Data
public class RoleData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//主键
	private Long parentId;//上级角色ID
	private String roleKey;//角色key
	private String roleName;//角色名称
	private Boolean isDeleted;//是否删除
	private String level;//层级
	private String description;//描述
	private String createdBy;//创建人员
	private Date createdAt;//创建日期
	private String updatedBy;//修改人员
	private Date updatedAt;//修改日期
	private Date lastUpdatedAt;//最后更新时间
}
