package com.learn.community.core.evaluation;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.common.model.Course;
import com.learn.community.common.model.Post;
import com.learn.community.core.base.BaseViewModel;

import java.util.List;

/**
 * view model
 */
public class EvaluationVM extends BaseViewModel {

    @SuppressLint("CheckResult")
    public LiveData<List<Course>> getData(Context context) {
        MutableLiveData<List<Course>> data = new MutableLiveData<>();
        runOnSubThread(() -> {
            List<Course> result = DBManager.getInstance(context).getCourseDao().queryAll();
            data.postValue(result);
        });
        return data;
    }

}
