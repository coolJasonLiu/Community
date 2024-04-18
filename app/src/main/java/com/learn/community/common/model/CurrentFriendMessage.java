package com.learn.community.common.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class CurrentFriendMessage implements MultiItemEntity {
    private Account account;
    private Message message;

    public CurrentFriendMessage(Account account, Message message) {
        this.account = account;
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public int getItemType() {
        if (message.isMyMessage()) {
            return 0;
        } else {
            return 1;
        }
    }
}
