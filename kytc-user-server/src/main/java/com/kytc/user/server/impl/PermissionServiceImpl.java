package com.kytc.user.server.impl;

import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.request.PermissionSearchRequest;
import com.kytc.user.server.service.PermissionService;
import com.kytc.user.request.PermissionRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.dao.data.PermissionData;
import com.kytc.user.dao.mapper.PermissionMapperEx;
import com.kytc.user.server.service.RolePermissionService;
import com.kytc.user.server.service.UserPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class PermissionServiceImpl implements PermissionService {
	private final PermissionMapperEx permissionMapperEx;
	private final RolePermissionService rolePermissionService;
	private final UserPermissionService userPermissionService;

	@Override
	public Long add(PermissionRequest request){
		PermissionData permissionData = BeanUtils.convert(request, PermissionData.class);
		permissionData.setCreatedAt(new Date());
		permissionData.setUpdatedAt(new Date());
		this.permissionMapperEx.insert(permissionData);
		if( null == permissionData ){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,"权限插入失败");
		}
		return permissionData.getId();
	}

	@Override
	public boolean update(PermissionRequest request){
		if( null != request ){
			PermissionData permissionData = BeanUtils.convert(request, PermissionData.class);
			permissionData.setUpdatedAt(new Date());
			return this.permissionMapperEx.updateByPrimaryKey(permissionData)>0;
		}
		return false;
	}

	@Override
	public PermissionResponse detail(Long id){
		PermissionData permissionData = this.permissionMapperEx.selectByPrimaryKey( id );
		if( null == permissionData ){
			return null;
		}
		return BeanUtils.convert(permissionData,PermissionResponse.class);
	}

	@Override
	public boolean delete(Long id){
		if( this.rolePermissionService.countByPermissionId(id)>0 ){
			throw new BaseException(BaseErrorCodeEnum.OPERATION_FAILED,"该权限已经关联角色,禁止删除");
		}
		if( this.userPermissionService.countByPermissionId(id)>0 ){
			throw new BaseException(BaseErrorCodeEnum.OPERATION_FAILED,"该权限已经关联用户,禁止删除");
		}
		return this.permissionMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<PermissionResponse> listByCondition(PermissionSearchRequest request){
		BasePageResponse<PermissionResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	private List<PermissionResponse> listByConditionData(PermissionSearchRequest request){
		request.init();
		List<PermissionData> list =  this.permissionMapperEx.listByCondition(request.getParentId(), request.getPermissionKey(),
				request.getPermissionName(), request.getUrl(), request.getLevel(), request.getDescription(), request.getStart(), request.getLimit());
		return BeanUtils.convert(list,PermissionResponse.class);
	}


	private Long countByConditionData(PermissionSearchRequest request){
		return this.permissionMapperEx.countByCondition(request.getParentId(), request.getPermissionKey(), request.getPermissionName(), request.getUrl(), request.getLevel(), request.getDescription());
	}
}
