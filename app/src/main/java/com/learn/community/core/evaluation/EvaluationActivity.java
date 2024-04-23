package com.learn.community.core.evaluation;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.learn.community.R;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.databinding.EvaluationActivityBinding;

public class EvaluationActivity extends BaseActivity<EvaluationActivityBinding, EvaluationVM> {
    private final EvaluationAdapter adapter = new EvaluationAdapter();
    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.add.setOnClickListener(v -> ActivityUtils.startActivity(AddActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getData(this).observe(this, adapter::setNewInstance);
    }

    @Override
    public int getLayoutId() {
        return R.layout.evaluation_activity;
    }
}
