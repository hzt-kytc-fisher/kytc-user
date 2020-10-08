package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.UserExtendData;

import java.util.List;

public interface UserExtendMapperEx extends UserExtendMapper {

	List<UserExtendData> listByCondition(Long userId, Long deptId, int start, int limit);

	Long countByCondition(Long userId, Long deptId);
}
