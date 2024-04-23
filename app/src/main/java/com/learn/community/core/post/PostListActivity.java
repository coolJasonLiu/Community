package com.learn.community.core.post;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.common.model.Post;
import com.learn.community.core.add.AddActivity;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.databinding.PostListFragmentBinding;
import com.learn.community.utils.LoginUtils;

import java.util.List;

public class PostListActivity extends BaseActivity<PostListFragmentBinding, PostMainVM> {
    public static final String TAG = "PostListFragment";
    private final PostListAdapter adapter = new PostListAdapter();
    private final PostMemberAdapter memberAdapter = new PostMemberAdapter();

    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.memberList.setAdapter(memberAdapter);
        binding.memberList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        viewModel.getData(this).observe(this, adapter::setNewInstance);
        viewModel.getMember(this).observe(this, memberAdapter::setNewInstance);
        binding.add.setOnClickListener((v) -> ActivityUtils.startActivity(AddActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        runOnSubThread(() -> {
            List<Post> data = DBManager.getInstance(this).getPostDao().queryAll();
            runOnUiThread(() -> adapter.setNewInstance(data));
            List<Account> accounts = DBManager.getInstance(this).getAccountDao().queryAll(LoginUtils.getInstance().getLoginUser());
            runOnUiThread(() -> memberAdapter.setNewInstance(accounts));
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.post_list_fragment;
    }
}
