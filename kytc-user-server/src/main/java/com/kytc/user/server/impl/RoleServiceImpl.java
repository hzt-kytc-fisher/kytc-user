package com.kytc.user.server.impl;

import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.request.RoleSearchRequest;
import com.kytc.user.server.service.RoleService;
import com.kytc.user.request.RoleRequest;
import com.kytc.user.response.RoleResponse;
import com.kytc.user.dao.data.RoleData;
import com.kytc.user.dao.mapper.RoleMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class RoleServiceImpl implements RoleService {
	private final RoleMapperEx roleMapperEx;

	@Override
	public Long add(RoleRequest request){
		RoleData roleData = BeanUtils.convert(request, RoleData.class);
		roleData.setCreatedAt(new Date());
		roleData.setUpdatedAt(new Date());
		this.roleMapperEx.insert(roleData);
		if( null == roleData ){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,"角色添加失败");
		}
		return roleData.getId();
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
	public BasePageResponse<RoleResponse> listByCondition(RoleSearchRequest request){
		BasePageResponse<RoleResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	@Override
	public List<RoleResponse> selectByLevel(String level) {
		List<RoleData> list = this.roleMapperEx.selectByLevel(level);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return BeanUtils.convert(list,RoleResponse.class);
	}

	private List<RoleResponse> listByConditionData(RoleSearchRequest request){
		request.init();
		List<RoleData> list =  this.roleMapperEx.listByCondition(request.getParentId(), request.getRoleKey(), request.getRoleName(),
				request.getLevel(), request.getDescription(),
				request.getStart(), request.getLimit());
		return BeanUtils.convert(list,RoleResponse.class);
	}


	private Long countByConditionData(RoleSearchRequest request){
		return this.roleMapperEx.countByCondition(request.getParentId(), request.getRoleKey(), request.getRoleName(), request.getLevel(), request.getDescription());
	}
}
