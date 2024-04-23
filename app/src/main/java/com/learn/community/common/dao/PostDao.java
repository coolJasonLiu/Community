package com.learn.community.common.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learn.community.common.model.Post;

import java.util.List;

@Dao
public interface PostDao {

    @Insert
    void insert(Post... postList);

    @Insert
    void insert(List<Post> postList);

    @Delete
    void delete(Post post);

    @Delete
    void delete(List<Post> postList);

    @Update
    void update(Post post);

    @Query("DELETE FROM post")
    void deleteAll();

    @Query("select * FROM post where id = :id")
    LiveData<Post> queryById(long id);

    @Query("select * FROM post order by post_time desc")
    List<Post> queryAll();

    @Query("select * FROM post where accountId = :accountId order by post_time desc")
    LiveData<List<Post>> queryAllMy(long accountId);

    @Query("select * FROM post where title like :keywords or content like :keywords order by post_time desc")
    List<Post> query(String keywords);

    @Query("select count(1) FROM post where accountId = :accountId")
    int count(long accountId);

}
