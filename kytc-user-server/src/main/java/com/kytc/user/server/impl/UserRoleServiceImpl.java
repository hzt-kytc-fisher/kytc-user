package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.UserRoleService;
import com.kytc.user.request.UserRoleRequest;
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
	public boolean add(UserRoleRequest request){
		if( null != request ){
			UserRoleData userRoleData = BeanUtils.convert(request, UserRoleData.class);
			userRoleData.setCreatedAt(new Date());
			userRoleData.setUpdatedAt(new Date());
			return this.userRoleMapperEx.insert(userRoleData)>0;
		}
		return false;
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
	public BasePageResponse<UserRoleResponse> listByCondition(UserRoleRequest request,int page, int pageSize){
		BasePageResponse<UserRoleResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<UserRoleResponse> listByConditionData(UserRoleRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<UserRoleData> list =  this.userRoleMapperEx.listByCondition(request.getUserId(), request.getRoleId(), start, pageSize);
		return BeanUtils.convert(list,UserRoleResponse.class);
	}


	private Long countByConditionData(UserRoleRequest request){
		return this.userRoleMapperEx.countByCondition(request.getUserId(), request.getRoleId());
	}
}
