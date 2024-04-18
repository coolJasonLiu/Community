package com.learn.community.utils;

import com.blankj.utilcode.util.SPUtils;
import com.learn.community.common.Constants;
import com.learn.community.common.model.Account;

/**
 * 保存登录信息
 */
public class LoginUtils {
    private Account loginUser;
    private LoginUtils() {

    }

    private static final class Holder {
        private static final LoginUtils UTILS = new LoginUtils();
    }

    public static LoginUtils getInstance() {
        return Holder.UTILS;
    }

    public void saveLoginUser(Account account) {
        this.loginUser = account;
        SPUtils.getInstance().put(Constants.KEY_LOGIN_ACCOUNT, account.getId());
    }

    public long getLoginUser() {
        if (null == loginUser) {
            return SPUtils.getInstance().getLong(Constants.KEY_LOGIN_ACCOUNT, -1L);
        } else {
            return loginUser.getId();
        }
    }

    public void logout() {
        SPUtils.getInstance().put(Constants.KEY_LOGIN_ACCOUNT, -1L);
        loginUser = null;
    }
}
