package com.learn.community.core.announcement;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.learn.community.R;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.evaluation.EvaluationVM;
import com.learn.community.databinding.AnnouncementLayoutBinding;

public class AnnouncementActivity  extends BaseActivity<AnnouncementLayoutBinding, AnnouncementVM> {
    private final AnnouncementAdapter adapter = new AnnouncementAdapter();
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
        return R.layout.announcement_layout;
    }
}
