package com.learn.community.common.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learn.community.common.model.Announcement;

import java.util.List;

@Dao
public interface AnnouncementDao {

    @Insert
    void insert(Announcement... announcementList);

    @Insert
    void insert(List<Announcement> announcementList);

    @Delete
    void delete(Announcement announcement);

    @Delete
    void delete(List<Announcement> announcementList);

    @Update
    void update(Announcement announcement);

    @Query("DELETE FROM announcement")
    void deleteAll();

    @Query("select * FROM announcement where id = :id")
    LiveData<Announcement> queryById(long id);

    @Query("select * FROM announcement")
    List<Announcement> queryAll();

}
