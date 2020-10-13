package com.kytc.user.server.impl;

import com.kytc.framework.cache.aop.RedisCache;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.dao.data.PermissionData;
import com.kytc.user.request.UserPermissionSearchRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.response.RoleResponse;
import com.kytc.user.server.service.RolePermissionService;
import com.kytc.user.server.service.RoleService;
import com.kytc.user.server.service.UserPermissionService;
import com.kytc.user.request.UserPermissionRequest;
import com.kytc.user.response.UserPermissionResponse;
import com.kytc.user.dao.data.UserPermissionData;
import com.kytc.user.dao.mapper.UserPermissionMapperEx;
import com.kytc.user.server.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserPermissionServiceImpl implements UserPermissionService {
	private final UserPermissionMapperEx userPermissionMapperEx;
	private final UserRoleService userRoleService;
	private final RolePermissionService rolePermissionService;
	private final RoleService roleService;

	@Override
	public boolean add(UserPermissionRequest request){
		if( null == request ){
			return false;
		}
		UserPermissionData data = this.userPermissionMapperEx.getByUserIdAndPermissionId(request.getUserId(),request.getPermissionId());
		if( null != data ){
			return true;
		}
		UserPermissionData userPermissionData = BeanUtils.convert(request, UserPermissionData.class);
		userPermissionData.setCreatedAt(new Date());
		userPermissionData.setUpdatedAt(new Date());
		return this.userPermissionMapperEx.insert(userPermissionData)>0;
	}

	@Override
	public boolean delete(Long id){
		return this.userPermissionMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<UserPermissionResponse> listByCondition(UserPermissionSearchRequest request){
		BasePageResponse<UserPermissionResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	@Override
	@RedisCache(cachePreKey = "user:permission:info",key = "#userId")
	public List<PermissionResponse> selectByUserId(Long userId) {
		List<PermissionData> list = this.userPermissionMapperEx.selectByUserId(userId);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return BeanUtils.convert(list,PermissionResponse.class);
	}

	@Override
	@RedisCache(cachePreKey = "user:permission:all:info",key = "#userId")
	public List<PermissionResponse> selectByUserIdAll(Long userId) {
		List<PermissionResponse> list = selectByUserId(userId);
		List<RoleResponse> roleResponses = this.userRoleService.selectByUserId(userId);
		if(CollectionUtils.isEmpty(roleResponses)){
			return list;
		}
		Set<Long> roleIds = new HashSet<>();
		for(RoleResponse roleResponse : roleResponses){
			roleIds.add(roleResponse.getId());
			String level = roleResponse.getLevel();
			if(StringUtils.isEmpty(level)||"-1".equals(level)){
				continue;
			}
			level = level+"-"+roleResponse.getId();
			List<RoleResponse> roleResponses1 = this.roleService.selectByLevel(level);
			if(CollectionUtils.isEmpty(roleResponses1)){
				continue;
			}
			roleIds.addAll(roleResponses1.stream().map(RoleResponse::getId).collect(Collectors.toList()));
		}
		List<PermissionResponse> list1 = this.rolePermissionService.selectByRoleIds(Arrays.asList(roleIds.toArray(new Long[]{})));
		if( CollectionUtils.isEmpty(list) ){
			if( CollectionUtils.isEmpty(list1) ){
				return list1;
			}
			return list1.stream().distinct().collect(Collectors.toList());
		}
		if(CollectionUtils.isEmpty(list1)){
			return list;
		}
		list.addAll(list1);
		return list.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public long countByPermissionId(Long permissionId) {
		UserPermissionSearchRequest request = new UserPermissionSearchRequest();
		request.setPermissionId(permissionId);
		return this.countByConditionData(request);
	}

	private List<UserPermissionResponse> listByConditionData(UserPermissionSearchRequest request){
		request.init();
		List<UserPermissionData> list =  this.userPermissionMapperEx.listByCondition(request.getUserId(), request.getPermissionId(),request.getStart(),request.getLimit());
		return BeanUtils.convert(list,UserPermissionResponse.class);
	}


	private Long countByConditionData(UserPermissionSearchRequest request){
		return this.userPermissionMapperEx.countByCondition(request.getUserId(), request.getPermissionId());
	}
}
