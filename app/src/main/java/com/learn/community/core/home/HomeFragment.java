package com.learn.community.core.home;

import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.core.add.AddActivity;
import com.learn.community.core.base.BaseFragment;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.databinding.HomeLayoutBinding;
import com.learn.community.utils.LoginUtils;

public class HomeFragment extends BaseFragment<HomeLayoutBinding, BaseViewModel> {
    Account mAccount;

    @Override
    public void onCreateView(View view) {
        long id = LoginUtils.getInstance().getLoginUser();
        DBManager.getInstance(getContext()).getAccountDao().queryById(id).observe(getViewLifecycleOwner(), account -> {
            mAccount = account;
            if (!TextUtils.isEmpty(account.getAvatar())) {
                Glide.with(getContext()).load(account.getAvatar())
                        .error(R.drawable.baseline_person_24)
                        .into(binding.avatar);
            }
        });
        DBManager.getInstance(getContext()).getPostDao().queryAllMy(LoginUtils.getInstance().getLoginUser())
                .observe(getViewLifecycleOwner(), list -> binding.posts.setText(list.size() + ""));
        DBManager.getInstance(getContext()).getFriendsDao().queryFollowMeList(LoginUtils.getInstance().getLoginUser())
                .observe(getViewLifecycleOwner(), list -> binding.followers.setText(list.size() + ""));
        binding.publish.setOnClickListener(v -> ActivityUtils.startActivity(AddActivity.class));
        binding.records.setOnClickListener(v -> ActivityUtils.startActivity(RecordListActivity.class));
        binding.setup.setOnClickListener(v -> ActivityUtils.startActivity(MineActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_layout;
    }
}
