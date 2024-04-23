package com.learn.community.core.evaluation;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Course;
import com.learn.community.common.model.Post;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.databinding.AddActivityBinding;
import com.learn.community.databinding.EvaluationAddActivityBinding;

public class AddActivity extends BaseActivity<EvaluationAddActivityBinding, BaseViewModel> {
    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        binding.save.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.title.getText())) {
                ToastUtils.showLong("Course Name should not be null");
                return;
            }
            if (TextUtils.isEmpty(binding.content.getText())) {
                ToastUtils.showLong("Evaluation should not be null");
                return;
            }
            Course course = new Course();
            course.setName(binding.title.getText().toString());
            course.setEvaluation(binding.content.getText().toString());
            runOnSubThread(() -> {
                DBManager.getInstance(this).getCourseDao().insert(course);
                runOnUiThread(this::finish);
            });
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.evaluation_add_activity;
    }
}
