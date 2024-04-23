package com.learn.community.common.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learn.community.common.model.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insert(Course... courseList);

    @Insert
    void insert(List<Course> courseList);

    @Delete
    void delete(Course course);

    @Delete
    void delete(List<Course> courseList);

    @Update
    void update(Course course);

    @Query("DELETE FROM course")
    void deleteAll();

    @Query("select * FROM course where id = :id")
    LiveData<Course> queryById(long id);

    @Query("select * FROM course")
    List<Course> queryAll();

    @Query("select * FROM course where accountId = :accountId")
    LiveData<List<Course>> queryAll(long accountId);


}
