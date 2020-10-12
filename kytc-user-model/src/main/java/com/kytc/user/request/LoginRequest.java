/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.kytc.user.request;

import com.kytc.user.enums.LoginTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 何志同
 * @Date: 2020/10/10 10:10
 * @Description:
 **/
@Data
public class LoginRequest implements Serializable {
    private LoginTypeEnum loginTypeEnum;
    private String loginKey;
    private String password;
}