package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.UserRoleData;

import java.util.List;

public interface UserRoleMapperEx extends UserRoleMapper {

	List<UserRoleData> listByCondition(Long userId, Long roleId, int start, int limit);

	Long countByCondition(Long userId, Long roleId);
}
