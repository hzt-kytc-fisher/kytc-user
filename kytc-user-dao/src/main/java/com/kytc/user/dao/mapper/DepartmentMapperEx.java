package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.DepartmentData;

import java.util.List;

public interface DepartmentMapperEx extends DepartmentMapper {

	List<DepartmentData> listByCondition(Long parentId, String deptKey, String deptName, int start, int limit);

	Long countByCondition(Long parentId, String deptKey, String deptName);

	DepartmentData getByParentId(Long parentId);

	List<DepartmentData> selectByIds(List<Long> list);

	DepartmentData getByDeptKey(String deptKey);
}
