package com.bj.house.common.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * 进行加盐操作，防止明文出现
 * Created by BJ on 2018/1/20.
 */
public class HashUtils {

    private static final HashFunction FUNCTION = Hashing.md5();

    private static final String SALT = "com.bj";

    public static String encryPassword(String password){
        //进行加盐加密操作
        HashCode hashCode = FUNCTION.hashString(password+SALT, Charset.forName("UTF-8"));
        return hashCode.toString();
    }

}
