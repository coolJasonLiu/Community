package com.learn.community.utils;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {
    private Handler handler = new Handler();
    int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
    private ExecutorService service = new ThreadPoolExecutor(
            corePoolSize,
            2 * corePoolSize,
            3_000,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingQueue<>());

    private ThreadPoolUtils() {

    }
    private static final class Holder {
        private static final ThreadPoolUtils UTILS = new ThreadPoolUtils();
    }

    public static ThreadPoolUtils getInstance() {
        return Holder.UTILS;
    }

    public void submit(Runnable runnable) {
        service.submit(runnable);
    }

    public void shutdown() {
        service.shutdown();
    }

    public void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}
