package com.learn.community.common.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learn.community.common.model.Account;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert
    void insert(Account... accounts);

    @Insert
    void insert(List<Account> accountList);

    @Delete
    void delete(Account account);

    @Delete
    void delete(List<Account> accounts);

    @Update
    void update(Account account);

    @Query("DELETE FROM account")
    void deleteAll();

    @Query("select * FROM account where username = :username")
    LiveData<Account> queryByUsername(String username);

    @Query("select * FROM account where id = :id")
    LiveData<Account> queryById(long id);

    @Query("select * FROM account where id = :id")
    Account queryByIdNormal(long id);

    @Query("select * FROM account where id != :id")
    List<Account> queryAll(long id);

}
