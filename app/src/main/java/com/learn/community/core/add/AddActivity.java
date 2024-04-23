package com.learn.community.core.add;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Post;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.databinding.AddActivityBinding;
import com.learn.community.utils.LoginUtils;

public class AddActivity extends BaseActivity<AddActivityBinding, BaseViewModel> {
    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        binding.save.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.title.getText())) {
                ToastUtils.showLong("Title should not be null");
                return;
            }
            if (TextUtils.isEmpty(binding.content.getText())) {
                ToastUtils.showLong("Content should not be null");
                return;
            }
            Post post = new Post();
            post.setContent(binding.content.getText().toString());
            post.setTitle(binding.title.getText().toString());
            post.setPostTime(System.currentTimeMillis());
            post.setAccountId(LoginUtils.getInstance().getLoginUser());
            runOnSubThread(() -> {
                DBManager.getInstance(this).getPostDao().insert(post);
                runOnUiThread(this::finish);
            });
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.add_activity;
    }
}
