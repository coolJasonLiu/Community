package com.learn.community.common.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "friends")
public class Friends implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long self;
    private long follow;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSelf() {
        return self;
    }

    public void setSelf(long self) {
        this.self = self;
    }

    public long getFollow() {
        return follow;
    }

    public void setFollow(long follow) {
        this.follow = follow;
    }
}
