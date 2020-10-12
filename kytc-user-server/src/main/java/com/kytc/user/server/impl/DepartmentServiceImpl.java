package com.kytc.user.server.impl;

import com.kytc.framework.exception.BaseErrorCodeEnum;
import com.kytc.framework.exception.BaseException;
import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.request.DepartmentSearchRequest;
import com.kytc.user.server.service.DepartmentService;
import com.kytc.user.request.DepartmentRequest;
import com.kytc.user.response.DepartmentResponse;
import com.kytc.user.dao.data.DepartmentData;
import com.kytc.user.dao.mapper.DepartmentMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


/**
 * @author hezhitong
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentMapperEx departmentMapperEx;

	@Override
	public boolean add(DepartmentRequest request){
		if( null != request ){
			DepartmentData departmentData = BeanUtils.convert(request, DepartmentData.class);
			if( null == departmentData.getParentId() ){
				departmentData.setParentId(-1L);
			}else{
				DepartmentResponse departmentResponse = this.detail(departmentData.getParentId());
				if( null == departmentResponse ){
					throw new BaseException(BaseErrorCodeEnum.SYSTEM_ERROR,"上级部门不存在");
				}
				String level = departmentResponse.getLevel();
				if(StringUtils.isEmpty(level)){
					level = String.valueOf(departmentData.getParentId());
				}else {
					level = level+"-"+departmentData.getParentId();
				}
				departmentData.setLevel(level);
			}

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
			departmentData.setParentId(null);
			departmentData.setLevel(null);
			return this.departmentMapperEx.updateByPrimaryKeySelective(departmentData)>0;
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
		DepartmentData departmentData = this.departmentMapperEx.getByParentId(id);
		if( null != departmentData ){
			throw new BaseException(BaseErrorCodeEnum.SYSTEM_ERROR,"该部门存在下级部门,禁止删除");
		}
		return this.departmentMapperEx.deleteByPrimaryKey(id)>0;
	}

	@Override
	public BasePageResponse<DepartmentResponse> listByCondition(DepartmentSearchRequest request){
		BasePageResponse<DepartmentResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(request.getPage());
		pageResponse.setPageSize(request.getPageSize());
		return pageResponse;
	}

	private List<DepartmentResponse> listByConditionData(DepartmentSearchRequest request){
		request.init();
		List<DepartmentData> list =  this.departmentMapperEx.listByCondition(request.getParentId(), request.getDeptKey(), request.getDeptName(), request.getStart(), request.getLimit());
		return BeanUtils.convert(list,DepartmentResponse.class);
	}


	private Long countByConditionData(DepartmentSearchRequest request){
		return this.departmentMapperEx.countByCondition(request.getParentId(), request.getDeptKey(), request.getDeptName());
	}
}
