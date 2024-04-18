package com.learn.community.core.post;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.common.model.Post;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.utils.LoginUtils;

import java.util.List;

/**
 * view model
 */
public class PostMainVM extends BaseViewModel {

    @SuppressLint("CheckResult")
    public LiveData<List<Post>> getData(Context context) {
        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        runOnSubThread(() -> {
            List<Post> result = DBManager.getInstance(context).getPostDao().queryAll();
            data.postValue(result);
        });
        return data;
    }

    public LiveData<List<Account>> getMember(Context context) {
        MutableLiveData<List<Account>> data = new MutableLiveData<>();
        runOnSubThread(() -> {
            List<Account> result = DBManager.getInstance(context).getAccountDao().queryAll(LoginUtils.getInstance().getLoginUser());
            data.postValue(result);
        });
        return data;
    }
}
