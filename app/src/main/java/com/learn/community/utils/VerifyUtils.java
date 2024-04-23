package com.learn.community.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.EncryptUtils;

/**
 * 密码加盐、校验
 */
public class VerifyUtils {
    public static final String SALT = "!@WQ%^COMMUNITY09&*12qw";
    private VerifyUtils() {

    }

    public static String encryptPassword(String password) {
        return EncryptUtils.encryptMD5ToString(password + SALT);
    }

    public static boolean verifyPassword(String password, String storage) {
        return TextUtils.equals(encryptPassword(password), storage);
    }
}
