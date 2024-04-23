package com.learn.community.core.announcement;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Announcement;
import com.learn.community.common.model.Course;
import com.learn.community.core.base.BaseViewModel;

import java.util.List;

/**
 * view model
 */
public class AnnouncementVM extends BaseViewModel {

    @SuppressLint("CheckResult")
    public LiveData<List<Announcement>> getData(Context context) {
        MutableLiveData<List<Announcement>> data = new MutableLiveData<>();
        runOnSubThread(() -> {
            List<Announcement> result = DBManager.getInstance(context).getAnnouncementDao().queryAll();
            data.postValue(result);
        });
        return data;
    }

}
