package com.learn.community.core.announcement;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Announcement;
import com.learn.community.common.model.Course;
import com.learn.community.utils.ThreadPoolUtils;

public class AnnouncementAdapter extends BaseQuickAdapter<Announcement, BaseViewHolder> {
    public AnnouncementAdapter() {
        super(R.layout.announcement_item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, Announcement bean) {
        holder.setText(R.id.tv_title, bean.getTitle());
        holder.setText(R.id.tv_content, bean.getContent());
        holder.getView(R.id.del).setOnClickListener(v -> ThreadPoolUtils.getInstance().submit(() -> {
            DBManager.getInstance(getContext()).getAnnouncementDao().delete(bean);
            getData().remove(bean);
            ThreadPoolUtils.getInstance().runOnUiThread(() -> notifyDataSetChanged());
        }));
    }
}
