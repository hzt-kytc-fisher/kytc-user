/*
 * Copyright 2020 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package com.kytc.user.server.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @Author: 何志同
 * @Date: 2020/10/10 10:27
 * @Description:
 **/
public class EncryptUtils {
    private EncryptUtils(){}
    private final static class EncryptUtilsHelper{
        private static final EncryptUtils INSTANCE = new EncryptUtils();
    }
    public static EncryptUtils getInstance(){
        return EncryptUtilsHelper.INSTANCE;
    }
    public String sha(String salt,String str){
        return Hashing.sha256().newHasher().putString(salt + str, Charsets.UTF_8).hash().toString();
    }

    public static void main(String[] args) {
        String salt = RandomStringUtils.randomAlphabetic(64);
        String p = EncryptUtils.getInstance().sha(salt,"123456111111111111111");
        System.out.println(salt+"\n"+p);
    }
}