package com.hammad.aiolos.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hammad.aiolos.model.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User WHERE txtEmail = :email")
    User getUser(String email);

    @Insert
    void insert(User user);
}
