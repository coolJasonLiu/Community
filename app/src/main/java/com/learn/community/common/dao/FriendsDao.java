package com.learn.community.common.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learn.community.common.model.Friends;

import java.util.List;

@Dao
public interface FriendsDao {

    @Insert
    void insert(Friends... friends);

    @Insert
    void insert(List<Friends> friends);

    @Delete
    void delete(Friends friends);

    @Delete
    void delete(List<Friends> friends);

    @Update
    void update(Friends friends);

    @Query("DELETE FROM friends")
    void deleteAll();

    @Query("select * FROM friends where follow = :followId and self = :selfId limit 1")
    Friends queryFollower(long followId, long selfId);

    @Query("select * FROM friends where id = :id")
    LiveData<Friends> queryFriend(long id);

    @Query("select * FROM friends where follow = :selfId and self = :followerId")
    Friends queryFollowMe(long selfId, long followerId);

    @Query("select * FROM friends where follow = :selfId")
    LiveData<List<Friends>> queryFollowMeList(long selfId);

    @Query("select * FROM friends where self = :selfId")
    LiveData<List<Friends>> queryMyFollow(long selfId);

    @Query("select * FROM friends")
    List<Friends> queryAll();

}
