package com.learn.community.core.home;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Post;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.databinding.RecordListActivityBinding;
import com.learn.community.utils.LoginUtils;

import java.util.List;

public class RecordListActivity extends BaseActivity<RecordListActivityBinding, RecordListVM> {
    public static final String TAG = "PostListFragment";
    private final RecordListAdapter adapter = new RecordListAdapter();

    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getData(this).observe(this, adapter::setNewInstance);
    }

    @Override
    public void onResume() {
        super.onResume();
            DBManager.getInstance(this).getPostDao()
                    .queryAllMy(LoginUtils.getInstance().getLoginUser())
                    .observe(this, adapter::setNewInstance);
    }

    @Override
    public int getLayoutId() {
        return R.layout.record_list_activity;
    }
}
