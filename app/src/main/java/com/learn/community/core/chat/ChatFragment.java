package com.learn.community.core.chat;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.learn.community.R;
import com.learn.community.core.announcement.AnnouncementActivity;
import com.learn.community.core.base.BaseFragment;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.core.evaluation.EvaluationActivity;
import com.learn.community.core.info.InfoActivity;
import com.learn.community.core.post.PostListActivity;
import com.learn.community.core.search.SearchListActivity;
import com.learn.community.databinding.ChatLayoutBinding;

public class ChatFragment extends BaseFragment<ChatLayoutBinding, BaseViewModel> {

    @Override
    public void onCreateView(View view) {
        binding.btnWebView.setOnClickListener(v -> ActivityUtils.startActivity(ChatViewActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.chat_layout;
    }
}