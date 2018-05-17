package com.example.anthonyeisenback.taskmanager2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = TaskCreator.class, version = 2)
@TypeConverters(DateConverter.class)

public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDAO();


}