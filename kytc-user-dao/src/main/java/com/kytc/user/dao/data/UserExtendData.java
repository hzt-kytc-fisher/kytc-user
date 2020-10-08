package com.kytc.user.dao.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户扩展信息
@Data
public class UserExtendData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//
	private Long userId;//用户ID
	private Long deptId;//部门ID
	private String createdBy;//创建人员
	private Date createdAt;//创建日期
	private String updatedBy;//修改人员
	private Date updatedAt;//修改日期
	private Date lastUpdatedAt;//最后更新时间
}
