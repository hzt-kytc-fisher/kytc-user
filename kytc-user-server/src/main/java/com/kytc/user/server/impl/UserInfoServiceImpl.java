package com.kytc.user.server.impl;

import com.kytc.framework.web.common.BasePageResponse;
import com.kytc.framework.web.utils.BeanUtils;
import com.kytc.user.server.service.UserInfoService;
import com.kytc.user.request.UserInfoRequest;
import com.kytc.user.response.UserInfoResponse;
import com.kytc.user.dao.data.UserInfoData;
import com.kytc.user.dao.mapper.UserInfoMapperEx;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UserInfoServiceImpl implements UserInfoService {
	private final UserInfoMapperEx userInfoMapperEx;

	@Override
	public boolean add(UserInfoRequest request){
		if( null != request ){
			UserInfoData userInfoData = BeanUtils.convert(request, UserInfoData.class);
			userInfoData.setCreatedAt(new Date());
			userInfoData.setUpdatedAt(new Date());
			return this.userInfoMapperEx.insert(userInfoData)>0;
		}
		return false;
	}

	@Override
	public boolean update(UserInfoRequest request){
		if( null != request ){
			UserInfoData userInfoData = BeanUtils.convert(request, UserInfoData.class);
			userInfoData.setUpdatedAt(new Date());
			return this.userInfoMapperEx.updateByPrimaryKey(userInfoData)>0;
		}
		return false;
	}

	@Override
	public UserInfoResponse detail(Long id){
		UserInfoData userInfoData = this.userInfoMapperEx.selectByPrimaryKey( id );
		if( null == userInfoData ){
			return null;
		}
		return BeanUtils.convert(userInfoData,UserInfoResponse.class);
	}

	@Override
	public boolean delete(Long id){
		UserInfoData userInfoData = new UserInfoData();
		userInfoData.setId(id);
		userInfoData.setIsDeleted(true);
		userInfoData.setUpdatedAt(new Date());
		return this.userInfoMapperEx.updateByPrimaryKeySelective(userInfoData)>0;
	}

	@Override
	public BasePageResponse<UserInfoResponse> listByCondition(UserInfoRequest request,int page, int pageSize){
		BasePageResponse<UserInfoResponse> pageResponse = new BasePageResponse<>();
		pageResponse.setRows(this.listByConditionData(request,page, pageSize));
		pageResponse.setTotal(this.countByConditionData(request));
		pageResponse.setPage(page);
		pageResponse.setPageSize(pageSize);
		return pageResponse;
	}

	private List<UserInfoResponse> listByConditionData(UserInfoRequest request,int page,int pageSize){
		int start = page * pageSize;
		List<UserInfoData> list =  this.userInfoMapperEx.listByCondition(request.getUsername(), request.getNickName(), request.getHeadPicture(), request.getIdCard(), request.getEnabled(), request.getMobile(), request.getIsDeleted(), request.getRegisterTime(), start, pageSize);
		return BeanUtils.convert(list,UserInfoResponse.class);
	}


	private Long countByConditionData(UserInfoRequest request){
		return this.userInfoMapperEx.countByCondition(request.getUsername(), request.getNickName(), request.getHeadPicture(), request.getIdCard(), request.getEnabled(), request.getMobile(), request.getIsDeleted(), request.getRegisterTime());
	}
}
