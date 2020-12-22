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
}