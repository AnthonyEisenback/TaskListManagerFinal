package com.example.anthonyeisenback.taskmanager2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    //Allow us to get all videogame
    //allow us to add a single game to the list
    //allows us to update the values of the existing games
    //allows us to delete a game from the library
    @Query("SELECT * FROM taskcreator")
    List<TaskCreator> getTasks();

    @Insert
    void addTask(TaskCreator taskCreator);

    @Update
    void updateTask(TaskCreator taskCreator);

    @Delete
    void deleteTask(TaskCreator taskCreator);

}

