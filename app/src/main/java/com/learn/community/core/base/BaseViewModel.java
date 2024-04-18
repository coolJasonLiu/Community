package com.learn.community.core.base;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.ViewModel;

import com.learn.community.utils.ThreadPoolUtils;


public class BaseViewModel extends ViewModel {
    private final Handler handler = new Handler(Looper.getMainLooper());

    public void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    public void runOnSubThread(Runnable runnable) {
        ThreadPoolUtils.getInstance().submit(runnable);
    }
}
