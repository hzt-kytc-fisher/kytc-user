package com.kytc.user.request;

import com.kytc.framework.web.common.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 何志同
 * @Date: 2020/10/10 12:05
 * @Description:
 **/
@Data
@ApiModel("部门查询信息 request")
public class DepartmentSearchRequest extends BasePageRequest implements Serializable {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("上级部门")
    private Long parentId;
    @ApiModelProperty("部门key")
    private String deptKey;
    @ApiModelProperty("部门名称")
    private String deptName;
}