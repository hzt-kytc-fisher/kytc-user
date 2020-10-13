package com.kytc.user.server.impl;

import com.google.common.collect.Lists;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * @author hezhitong
 */
@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentMapperEx departmentMapperEx;

	@Override
	public Long add(DepartmentRequest request){
		DepartmentResponse departmentResponse = this.getByDeptKey(request.getDeptKey());
		if( null != departmentResponse ){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,request.getDeptKey()+"已存在");
		}
		DepartmentData departmentData = BeanUtils.convert(request, DepartmentData.class);
		if( null == departmentData.getParentId() ){
			departmentData.setParentId(-1L);
			departmentData.setLevel("-1");
		}else{
			DepartmentResponse response = this.detail(departmentData.getParentId());
			if( null == response ){
				throw new BaseException(BaseErrorCodeEnum.SYSTEM_ERROR,"上级部门不存在");
			}
			String level = response.getLevel();
			if(StringUtils.isEmpty(level)){
				level = String.valueOf(departmentData.getParentId());
			}else {
				level = level+"-"+departmentData.getParentId();
			}
			departmentData.setLevel(level);
		}
		departmentData.setCreatedAt(new Date());
		departmentData.setUpdatedAt(new Date());
		this.departmentMapperEx.insert(departmentData);
		if( null == departmentData ){
			throw new BaseException(BaseErrorCodeEnum.DATA_NOT_FOUND,"添加数据失败");
		}
		return departmentData.getId();
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

	@Override
	public List<DepartmentResponse> select(Long deptId) {
		List<DepartmentResponse> list = null;
		if( null == deptId ){
			DepartmentSearchRequest departmentSearchRequest = new DepartmentSearchRequest();
			list = this.listByConditionData(departmentSearchRequest);
		}else{
			DepartmentData departmentData = this.departmentMapperEx.selectByPrimaryKey(deptId);
			if( null == departmentData ){
				return Lists.newArrayList();
			}
			if(StringUtils.isEmpty(departmentData.getLevel())||"-1".equals(departmentData.getLevel())){
				list = Arrays.asList(BeanUtils.convert(departmentData,DepartmentResponse.class));
			}
			String level = departmentData.getLevel();
			String[] levelArr = level.split("-");
			List<Long> ids = Arrays.asList(levelArr).stream().map(l->Long.valueOf(l)).collect(Collectors.toList());
			list = this.selectByIds(ids);
			if(CollectionUtils.isEmpty(list)){
				list = new ArrayList<>();
			}
			list.add(BeanUtils.convert(departmentData,DepartmentResponse.class));
		}
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		Map<Long,List<DepartmentResponse>> result = list.stream().collect(Collectors.groupingBy(DepartmentResponse::getParentId));
		for(DepartmentResponse departmentResponse:list){
			departmentResponse.setChildren(result.get(departmentResponse.getId()));
		}
		return result.get(-1L);
	}

	@Override
	public List<DepartmentResponse> selectByIds(List<Long> ids) {
		List<DepartmentData> list = this.departmentMapperEx.selectByIds(ids);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return BeanUtils.convert(list,DepartmentResponse.class);
	}

	@Override
	public DepartmentResponse getByDeptKey(String deptKey) {
		DepartmentData departmentData = this.departmentMapperEx.getByDeptKey(deptKey);
		if( null == departmentData ){
			return null;
		}
		return BeanUtils.convert(departmentData,DepartmentResponse.class);
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
