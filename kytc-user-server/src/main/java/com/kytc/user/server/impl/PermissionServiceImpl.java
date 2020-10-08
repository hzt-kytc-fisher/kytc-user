package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.PermissionService;
import com.kytc.user.request.PermissionRequest;
import com.kytc.user.response.PermissionResponse;
import com.kytc.user.dao.data.PermissionData;
import com.kytc.user.dao.mapper.PermissionMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class PermissionServiceImpl implements PermissionService {
	private final PermissionMapperEx permissionMapperEx;

	@Override
	public boolean add(PermissionRequest request){
		if( null != request ){
			PermissionData permissionData = BeanUtils.convert(request, PermissionData.class);
			permissionData.setCreatedAt(new Date());
			permissionData.setUpdatedAt(new Date());
			return this.permissionMapperEx.insert(permissionData)>0;
		}
		return false;
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
		return this.permissionMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<PermissionResponse> listByCondition(PermissionRequest request,int page, int pageSize){
		BasePageResponse<PermissionResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<PermissionResponse> listByConditionData(PermissionRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<PermissionData> list =  this.permissionMapperEx.listByCondition(request.getParentId(), request.getPermissionKey(), request.getPermissionName(), request.getUrl(), request.getLevel(), request.getDescription(), request.getIdDeleted(), start, pageSize);
		return BeanUtils.convert(list,PermissionResponse.class);
	}


	private Long countByConditionData(PermissionRequest request){
		return this.permissionMapperEx.countByCondition(request.getParentId(), request.getPermissionKey(), request.getPermissionName(), request.getUrl(), request.getLevel(), request.getDescription(), request.getIdDeleted());
	}
}
