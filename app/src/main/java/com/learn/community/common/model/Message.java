package com.learn.community.common.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "message")
public class Message {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String message;
    private long selfId;
    private long friendId;
    private long time;
    private boolean myMessage;

    public void setMyMessage(boolean myMessage) {
        this.myMessage = myMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSelfId() {
        return selfId;
    }

    public void setSelfId(long selfId) {
        this.selfId = selfId;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isMyMessage() {
        return myMessage;
    }
}
