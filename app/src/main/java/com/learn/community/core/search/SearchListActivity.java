package com.learn.community.core.search;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.common.model.Post;
import com.learn.community.core.add.AddActivity;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.post.PostMemberAdapter;
import com.learn.community.databinding.PostListFragmentBinding;
import com.learn.community.databinding.SearchListActivityBinding;

import java.util.List;

public class SearchListActivity extends BaseActivity<SearchListActivityBinding, SearchVM> {
    public static final String TAG = "SearchListActivity";
    private final SearchListAdapter adapter = new SearchListAdapter();
    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.search.setOnClickListener(v -> {
            String text = String.valueOf(binding.etSearch.getText());
            if (TextUtils.isEmpty(text)) {
                ToastUtils.showLong("Please input keywords");
            } else {
                viewModel.getData(this, text).observe(this, adapter::setNewInstance);
            }
        });
        // 文本改变后，自动搜索
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                viewModel.getData(SearchListActivity.this, text)
                        .observe(SearchListActivity.this, adapter::setNewInstance);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.search_list_activity;
    }
}
