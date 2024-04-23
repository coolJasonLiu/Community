package com.learn.community.core.search;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.common.model.Post;
import com.learn.community.core.base.BaseViewModel;

import java.util.List;

/**
 * view model
 */
public class SearchVM extends BaseViewModel {

    @SuppressLint("CheckResult")
    public LiveData<List<Post>> getData(Context context, String keywords) {
        MutableLiveData<List<Post>> data = new MutableLiveData<>();
        runOnSubThread(() -> {
            List<Post> result = DBManager.getInstance(context).getPostDao().query("%" + keywords + "%");
            data.postValue(result);
        });
        return data;
    }

}
