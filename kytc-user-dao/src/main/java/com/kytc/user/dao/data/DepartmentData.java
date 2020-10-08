package com.kytc.user.dao.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//部门信息
@Data
public class DepartmentData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//主键
	private Long parentId;//上级部门
	private String deptKey;//部门key
	private String deptName;//部门名称
	private String address;//地址
	private String remark;//备注
	private String level;//层级
	private String createdBy;//创建人员
	private Date createdAt;//创建日期
	private String updatedBy;//修改人员
	private Date updatedAt;//修改日期
	private Date lastUpdatedAt;//最后更新时间
}
