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