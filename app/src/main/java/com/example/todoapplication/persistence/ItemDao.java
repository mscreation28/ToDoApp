package com.example.todoapplication.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapplication.models.Item;

import java.util.List;

@androidx.room.Dao
public interface ItemDao {

    @Insert
    long[] insertItem(Item... items);

    @Query("SELECT * FROM items")
    LiveData<List<Item>> getItems();

    @Query("SELECT * FROM items WHERE id = :id")
    List<Item> getNotesWithCustomQuery(int id);

    @Query("SELECT * FROM items WHERE done = 0")
    LiveData<List<Item>> getTodoList();

    @Query("SELECT * FROM items WHERE done = 1")
    LiveData<List<Item>> getDoneList();

    @Update
    int update(Item... items);
}
