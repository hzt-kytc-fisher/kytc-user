package com.kytc.user.server.impl;

import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.UserRoleService;
import com.kytc.user.request.UserRoleRequest;
import com.kytc.user.request.UserRoleSearchRequest;
import com.kytc.user.response.UserRoleResponse;
import com.kytc.user.dao.data.UserRoleData;
import com.kytc.user.dao.mapper.UserRoleMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserRoleServiceImpl implements UserRoleService {
	private final UserRoleMapperEx userRoleMapperEx;

	@Override
	public Long add(UserRoleRequest request){
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
	public boolean update(UserRoleRequest request){
		if( null != request ){
			UserRoleData userRoleData = BeanUtils.convert(request, UserRoleData.class);
			userRoleData.setUpdatedAt(new Date());
			return this.userRoleMapperEx.updateByPrimaryKey(userRoleData)>0;
		}
		return false;
	}

	@Override
	public UserRoleResponse detail(Long id){
		UserRoleData userRoleData = this.userRoleMapperEx.selectByPrimaryKey( id );
		if( null == userRoleData ){
			return null;
		}
		return BeanUtils.convert(userRoleData,UserRoleResponse.class);
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
