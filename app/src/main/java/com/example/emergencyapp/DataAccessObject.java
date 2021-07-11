package com.example.emergencyapp;

import androidx.annotation.BinderThread;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DataAccessObject {
    @Query("SELECT * FROM friends")
    List<Friends> getAll();

    //@Query("SELECT * FROM friends WHERE MyName = :NAME & MyPass = :pass1")
    //public Friends UserId(String NAME, String pass1);

    @Query("SELECT * FROM friends WHERE Name_name == :newName")
    Friends findNewName(String newName);

   // @Query("SELECT * FROM friends WHERE Id IN (:userIds)")
  //  List<Friends> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM friends WHERE MyName == :NAME000 AND " +
            "MyPass == :mypass000")
    Friends findByName(String NAME000, String mypass000);

    @Insert
    void insert(Friends friends);

    @Delete
    void delete(Friends friends);

    @Update
    void update(Friends friends);





}
