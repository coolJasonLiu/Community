package com.learn.community.core.post;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.common.model.Friends;
import com.learn.community.utils.LoginUtils;
import com.learn.community.utils.ThreadPoolUtils;

public class PostMemberAdapter extends BaseQuickAdapter<Account, BaseViewHolder> {
    public PostMemberAdapter() {
        super(R.layout.member_item);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder holder, Account account) {
        holder.setText(R.id.name, account.getUsername());
        if (!TextUtils.isEmpty(account.getAvatar())) {
            Glide.with(getContext()).load(account.getAvatar()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    // 请求失败，隐藏图片
                    holder.setGone(R.id.avatar, true);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).error(R.drawable.baseline_person_24).into((ImageView) holder.getView(R.id.avatar));
        }

        ThreadPoolUtils.getInstance().submit(() -> {
            Friends f = DBManager.getInstance(getContext()).getFriendsDao().queryFollower(account.getId(), LoginUtils.getInstance().getLoginUser());
            ThreadPoolUtils.getInstance().runOnUiThread(() -> {
                if (null == f) {
                    holder.setText(R.id.follow, "follow");
                } else {
                    holder.setText(R.id.follow, "followed");
                }
            });
        });

        holder.getView(R.id.follow).setOnClickListener(v -> ThreadPoolUtils.getInstance().submit(() -> {
            Friends f = DBManager.getInstance(getContext()).getFriendsDao().queryFollower(account.getId(), LoginUtils.getInstance().getLoginUser());
            if (null == f) {
                Friends friends = new Friends();
                friends.setSelf(LoginUtils.getInstance().getLoginUser());
                friends.setFollow(account.getId());
                DBManager.getInstance(getContext()).getFriendsDao().insert(friends);
                ThreadPoolUtils.getInstance().runOnUiThread(() -> holder.setText(R.id.follow, "followed"));
            } else {
                DBManager.getInstance(getContext()).getFriendsDao().delete(f);
                ThreadPoolUtils.getInstance().runOnUiThread(() -> holder.setText(R.id.follow, "follow"));
            }
        }));
    }
}
