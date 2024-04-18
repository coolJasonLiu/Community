package com.learn.community.common.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.learn.community.common.dao.AccountDao;
import com.learn.community.common.dao.AnnouncementDao;
import com.learn.community.common.dao.CourseDao;
import com.learn.community.common.dao.FriendsDao;
import com.learn.community.common.dao.MessageDao;
import com.learn.community.common.dao.PostDao;
import com.learn.community.common.model.Account;
import com.learn.community.common.model.Announcement;
import com.learn.community.common.model.Course;
import com.learn.community.common.model.Friends;
import com.learn.community.common.model.Message;
import com.learn.community.common.model.Post;

/**
 * room数据库管理，管理表和Dao
 */
@Database(entities = {Account.class, Post.class, Course.class,
        Announcement.class, Friends.class, Message.class}, version = 1, exportSchema = false)
public abstract class DBManager extends RoomDatabase {
    private static final String DB_NAME = "TourismDB.db";
    private static DBManager instance;

    public static synchronized DBManager getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static DBManager create(final Context context) {

        Builder<DBManager> dbBuilder = Room.databaseBuilder(
                context, DBManager.class, DB_NAME);

        dbBuilder.addMigrations();

        return dbBuilder.build();
    }

    public abstract AccountDao getAccountDao();
    public abstract PostDao getPostDao();
    public abstract CourseDao getCourseDao();
    public abstract AnnouncementDao getAnnouncementDao();
    public abstract FriendsDao getFriendsDao();
    public abstract MessageDao getMessageDao();

}
