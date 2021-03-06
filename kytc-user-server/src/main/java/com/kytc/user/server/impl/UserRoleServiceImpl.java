package com.kytc.user.server.impl;

import com.kytc.framework.cache.aop.RedisCache;
import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.dao.data.RoleData;
import com.kytc.user.response.RoleResponse;
import com.kytc.user.server.service.UserRoleService;
import com.kytc.user.request.UserRoleRequest;
import com.kytc.user.request.UserRoleSearchRequest;
import com.kytc.user.response.UserRoleResponse;
import com.kytc.user.dao.data.UserRoleData;
import com.kytc.user.dao.mapper.UserRoleMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserRoleServiceImpl implements UserRoleService {
	private final UserRoleMapperEx userRoleMapperEx;

	@Override
	public Long add(UserRoleRequest request){
		UserRoleData data = this.userRoleMapperEx.getByUserIdAndRoleId(request.getUserId(),request.getRoleId());
		if( null != data ){
			return data.getId();
		}
		UserRoleData userRoleData = BeanUtils.convert(request, UserRoleData.class);
		userRoleData.setCreatedAt(new Date());
		userRoleData.setUpdatedAt(new Date());
		this.userRoleMapperEx.insert(userRoleData);
		if(null == userRoleData.getId()){
			throw new BaseException(BaseErrorCodeEnum.SYSTEM_ERROR,"添加失败");
		}
		return userRoleData.getId();
	}

	@Override
	@RedisCache(cachePreKey = "user:role:info",key = "#userId")
	public List<RoleResponse> selectByUserId(Long userId){
		List<RoleData> list = this.userRoleMapperEx.selectByUserId( userId );
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return BeanUtils.convert(list,RoleResponse.class);
	}

	@Override
	public boolean delete(Long id){
		return this.userRoleMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<UserRoleResponse> listByCondition( UserRoleSearchRequest request ){
		BasePageResponse<UserRoleResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData( request ));
		pageResponse.setTotal(this.countByConditionData( request ));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	private List<UserRoleResponse> listByConditionData( UserRoleSearchRequest request ){
		request.init();
		List<UserRoleData> list =  this.userRoleMapperEx.listByCondition(request.getId(), request.getUserId(), request.getRoleId(), request.getStart(), request.getLimit());
		return BeanUtils.convert(list,UserRoleResponse.class);
	}


	private Long countByConditionData(UserRoleSearchRequest request){
		return this.userRoleMapperEx.countByCondition(request.getId(), request.getUserId(), request.getRoleId());
	}

	@Override
	public boolean delete( Long userId, Long roleId){
		return this.userRoleMapperEx.deleteByUserIdAndRoleId(userId,roleId)>0;
	}
}
