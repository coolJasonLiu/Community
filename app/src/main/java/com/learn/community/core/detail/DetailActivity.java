package com.learn.community.core.detail;

import com.learn.community.R;
import com.learn.community.common.Constants;
import com.learn.community.common.model.Post;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.databinding.DetailLayoutBinding;

/**
 * 新闻详情页
 */
public class DetailActivity extends BaseActivity<DetailLayoutBinding, PostVM>{
    public static final String TAG = "DetailActivity";
    private Post bean;

    @Override
    public void onCreate() {
        binding.setVm(viewModel);
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());

        bean = (Post) getIntent().getSerializableExtra(Constants.KEY_POST);
        if (null != bean) {
            viewModel.initData(bean);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.detail_layout;
    }
}
