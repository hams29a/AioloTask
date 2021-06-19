package com.hammad.aiolos.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hammad.aiolos.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
abstract public class UserDataBase extends RoomDatabase {

    private static UserDataBase instance;

    public abstract UserDAO getUserDao();

    public static synchronized UserDataBase getDatabase(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDataBase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
