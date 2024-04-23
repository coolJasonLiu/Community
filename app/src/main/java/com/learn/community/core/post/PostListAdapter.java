package com.learn.community.core.post;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.learn.community.R;
import com.learn.community.common.Constants;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Post;
import com.learn.community.core.detail.DetailActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.learn.community.utils.ThreadPoolUtils;

public class PostListAdapter extends BaseQuickAdapter<Post, BaseViewHolder> {
    public PostListAdapter() {
        super(R.layout.main_post_item);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder holder, Post newsBean) {
        holder.setText(R.id.tv_title, newsBean.getTitle());
        // 获取文章内容描述，取字符串前100个来展示
        String description;
        if (TextUtils.isEmpty(newsBean.getContent())) {
            description = "";
        } else if (newsBean.getContent().length() <= 100) {
            description = newsBean.getContent();
        } else {
            description = newsBean.getContent().substring(0, 100);
        }
        holder.setText(R.id.tv_content, description);
        // 点击item，跳转到详情页面
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(Constants.KEY_POST, newsBean);
            ActivityUtils.startActivity(intent);
        });
        holder.getView(R.id.del).setOnClickListener(v -> ThreadPoolUtils.getInstance().submit(() -> {
            DBManager.getInstance(getContext()).getPostDao().delete(newsBean);
            getData().remove(newsBean);
            ThreadPoolUtils.getInstance().runOnUiThread(() -> notifyDataSetChanged());
        }));
    }
}
