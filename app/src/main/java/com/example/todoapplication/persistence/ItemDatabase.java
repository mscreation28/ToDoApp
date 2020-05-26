package com.example.todoapplication.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todoapplication.models.Item;

@Database(entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "items_db";

    private static ItemDatabase instance;

    static ItemDatabase getInstance(final Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),ItemDatabase.class,DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract ItemDao getItemDao();
}
