package com.learn.community.common.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learn.community.common.model.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert
    void insert(Message... messages);

    @Insert
    void insert(List<Message> messages);

    @Delete
    void delete(Message message);

    @Delete
    void delete(List<Message> messages);

    @Update
    void update(Message message);

    @Query("DELETE FROM message")
    void deleteAll();

    @Query("select * FROM message where id = :id")
    LiveData<Message> queryById(long id);

    @Query("select * FROM message where id != :id")
    List<Message> queryAll(long id);
    @Query("select * FROM message where selfId = :sendId or friendId = :sendId limit 1")
    Message queryMessage(long sendId);

    @Query("select * FROM message where (friendId = :friendId and selfId = :selfId) or (friendId = :selfId and selfId = :friendId) order by time asc")
    LiveData<List<Message>> queryFriendMessage(long friendId, long selfId);

    @Query("select * FROM message where selfId = :selfId and myMessage = :myMessage order by time desc")
    List<Message> queryCurrentMessage(long selfId, boolean myMessage);
}
