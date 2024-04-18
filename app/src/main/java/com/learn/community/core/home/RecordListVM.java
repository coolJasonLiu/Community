package com.learn.community.core.home;

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
public class RecordListVM extends BaseViewModel {

    @SuppressLint("CheckResult")
    public LiveData<List<Post>> getData(Context context) {
        return DBManager.getInstance(context).getPostDao().queryAllMy(LoginUtils.getInstance().getLoginUser());
    }
}
