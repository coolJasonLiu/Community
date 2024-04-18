package com.learn.community.core.community;

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
import com.learn.community.databinding.CommunityLayoutBinding;

public class CommunityFragment extends BaseFragment<CommunityLayoutBinding, BaseViewModel> {

    @Override
    public void onCreateView(View view) {
        binding.search.setOnClickListener(v -> ActivityUtils.startActivity(SearchListActivity.class));

        binding.studentForum.setOnClickListener(v -> ActivityUtils.startActivity(PostListActivity.class));
        binding.universityInformation.setOnClickListener(v -> ActivityUtils.startActivity(InfoActivity.class));
        binding.courseEvaluation.setOnClickListener(v -> ActivityUtils.startActivity(EvaluationActivity.class));
        binding.announcements.setOnClickListener(v -> ActivityUtils.startActivity(AnnouncementActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.community_layout;
    }
}
