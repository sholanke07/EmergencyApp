package com.example.emergencyapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Friends.class}, version = 2, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DataAccessObject Dao();
}

