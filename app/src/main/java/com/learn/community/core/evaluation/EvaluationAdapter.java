package com.learn.community.core.evaluation;

import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.learn.community.R;
import com.learn.community.common.Constants;
import com.learn.community.common.model.Course;
import com.learn.community.common.model.Post;
import com.learn.community.core.detail.DetailActivity;

public class EvaluationAdapter extends BaseQuickAdapter<Course, BaseViewHolder> {
    public EvaluationAdapter() {
        super(R.layout.evaluation_item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, Course bean) {
        holder.setText(R.id.tv_title, bean.getName());
        holder.setText(R.id.tv_content, bean.getEvaluation());
    }
}
