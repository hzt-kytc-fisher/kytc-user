package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.DepartmentService;
import com.kytc.user.request.DepartmentRequest;
import com.kytc.user.response.DepartmentResponse;
import com.kytc.user.dao.data.DepartmentData;
import com.kytc.user.dao.mapper.DepartmentMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentMapperEx departmentMapperEx;

	@Override
	public boolean add(DepartmentRequest request){
		if( null != request ){
			DepartmentData departmentData = BeanUtils.convert(request, DepartmentData.class);
			departmentData.setCreatedAt(new Date());
			departmentData.setUpdatedAt(new Date());
			return this.departmentMapperEx.insert(departmentData)>0;
		}
		return false;
	}

	@Override
	public boolean update(DepartmentRequest request){
		if( null != request ){
			DepartmentData departmentData = BeanUtils.convert(request, DepartmentData.class);
			departmentData.setUpdatedAt(new Date());
			return this.departmentMapperEx.updateByPrimaryKey(departmentData)>0;
		}
		return false;
	}

	@Override
	public DepartmentResponse detail(Long id){
		DepartmentData departmentData = this.departmentMapperEx.selectByPrimaryKey( id );
		if( null == departmentData ){
			return null;
		}
		return BeanUtils.convert(departmentData,DepartmentResponse.class);
	}

	@Override
	public boolean delete(Long id){
		return this.departmentMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<DepartmentResponse> listByCondition(DepartmentRequest request,int page, int pageSize){
		BasePageResponse<DepartmentResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<DepartmentResponse> listByConditionData(DepartmentRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<DepartmentData> list =  this.departmentMapperEx.listByCondition(request.getParentId(), request.getDeptKey(), request.getDeptName(), request.getAddress(), request.getRemark(), request.getLevel(), start, pageSize);
		return BeanUtils.convert(list,DepartmentResponse.class);
	}


	private Long countByConditionData(DepartmentRequest request){
		return this.departmentMapperEx.countByCondition(request.getParentId(), request.getDeptKey(), request.getDeptName(), request.getAddress(), request.getRemark(), request.getLevel());
	}
}
