package com.kytc.user.dao.mapper;


import com.kytc.user.dao.data.UserRoleData;

import java.util.List;

public interface UserRoleMapperEx extends UserRoleMapper {

	List<UserRoleData> listByCondition(Long id, Long userId, Long roleId, int start, int limit);

	Long countByCondition(Long id, Long userId, Long roleId);

	Integer deleteByUserIdAndRoleId(Long userId, Long roleId);

	UserRoleData getByUserIdAndRoleId(Long userId, Long roleId);
}
