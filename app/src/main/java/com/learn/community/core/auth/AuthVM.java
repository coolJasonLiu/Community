package com.learn.community.core.auth;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.utils.ThreadPoolUtils;
import com.learn.community.utils.VerifyUtils;

public class AuthVM extends BaseViewModel {

    public LiveData<Account> getAccount(Context context, String username) {
        return DBManager.getInstance(context).getAccountDao().queryByUsername(username);
    }

    public void saveAccount(Context context, String username, String password, Runnable runnable) {
        ThreadPoolUtils.getInstance().submit(() -> {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(VerifyUtils.encryptPassword(password));
            DBManager.getInstance(context).getAccountDao().insert(account);
            runnable.run();
        });
    }

}
