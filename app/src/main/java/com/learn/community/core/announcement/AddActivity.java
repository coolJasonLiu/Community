package com.learn.community.core.announcement;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Announcement;
import com.learn.community.common.model.Course;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.databinding.AnnouncementAddActivityBinding;
import com.learn.community.databinding.EvaluationAddActivityBinding;

public class AddActivity extends BaseActivity<AnnouncementAddActivityBinding, BaseViewModel> {
    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        binding.save.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.title.getText())) {
                ToastUtils.showLong("Announcement Title should not be null");
                return;
            }
            if (TextUtils.isEmpty(binding.content.getText())) {
                ToastUtils.showLong("Announcement Content should not be null");
                return;
            }
            Announcement announcement = new Announcement();
            announcement.setTitle(binding.title.getText().toString());
            announcement.setContent(binding.content.getText().toString());
            runOnSubThread(() -> {
                DBManager.getInstance(this).getAnnouncementDao().insert(announcement);
                runOnUiThread(this::finish);
            });
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.announcement_add_activity;
    }
}
