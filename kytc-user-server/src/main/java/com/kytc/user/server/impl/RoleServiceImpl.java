package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.RoleService;
import com.kytc.user.request.RoleRequest;
import com.kytc.user.response.RoleResponse;
import com.kytc.user.dao.data.RoleData;
import com.kytc.user.dao.mapper.RoleMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class RoleServiceImpl implements RoleService {
	private final RoleMapperEx roleMapperEx;

	@Override
	public boolean add(RoleRequest request){
		if( null != request ){
			RoleData roleData = BeanUtils.convert(request, RoleData.class);
			roleData.setCreatedAt(new Date());
			roleData.setUpdatedAt(new Date());
			return this.roleMapperEx.insert(roleData)>0;
		}
		return false;
	}

	@Override
	public boolean update(RoleRequest request){
		if( null != request ){
			RoleData roleData = BeanUtils.convert(request, RoleData.class);
			roleData.setUpdatedAt(new Date());
			return this.roleMapperEx.updateByPrimaryKey(roleData)>0;
		}
		return false;
	}

	@Override
	public RoleResponse detail(Long id){
		RoleData roleData = this.roleMapperEx.selectByPrimaryKey( id );
		if( null == roleData ){
			return null;
		}
		return BeanUtils.convert(roleData,RoleResponse.class);
	}

	@Override
	public boolean delete(Long id){
		RoleData roleData = new RoleData();
		roleData.setId(id);
		roleData.setIsDeleted(true);
		roleData.setUpdatedAt(new Date());
		return this.roleMapperEx.updateByPrimaryKeySelective(roleData)>0;
	}

	@Override
	public BasePageResponse<RoleResponse> listByCondition(RoleRequest request,int page, int pageSize){
		BasePageResponse<RoleResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<RoleResponse> listByConditionData(RoleRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<RoleData> list =  this.roleMapperEx.listByCondition(request.getParentId(), request.getRoleKey(), request.getRoleName(), request.getIsDeleted(), request.getLevel(), request.getDescription(), start, pageSize);
		return BeanUtils.convert(list,RoleResponse.class);
	}


	private Long countByConditionData(RoleRequest request){
		return this.roleMapperEx.countByCondition(request.getParentId(), request.getRoleKey(), request.getRoleName(), request.getIsDeleted(), request.getLevel(), request.getDescription());
	}
}
