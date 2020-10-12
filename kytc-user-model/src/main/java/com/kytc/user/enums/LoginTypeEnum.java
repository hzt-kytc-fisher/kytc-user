/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.kytc.user.enums;

import com.kytc.framework.common.base.BaseEnum;
import lombok.Getter;

/**
 * @Author: 何志同
 * @Date: 2020/10/9 19:27
 * @Description:
 **/
@Getter
public enum LoginTypeEnum implements BaseEnum {
    MOBILE(0,"mobile","手机号验证码登录"),
    MOBILE_PASSWORD(2,"mobile-password","手机号密码登录"),
    USERNAME_PASSWORD(4,"username-password","用户名密码登录"),
    EMAIL_PASSWORD(8,"email-password","邮箱密码登录"),
    QQ(16,"QQ","QQ第三方登录"),
    WECHAT(32,"wechat","微信第三方登录"),
    ENTERPRISE_WECHAT(64,"enterprise-wechat","企业微信第三方登录"),
    WEIBO(128,"weibo","微博第三方登录");
    int code;
    String value;
    String desc;
    LoginTypeEnum(int code,String value,String desc){
        this.code = code;
        this.value = value;
        this.desc = desc;
    }
    @Override
    public String getDesc() {
        return this.desc;
    }
}