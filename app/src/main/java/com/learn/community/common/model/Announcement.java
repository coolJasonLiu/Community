package com.learn.community.common.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "announcement")
public class Announcement {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
