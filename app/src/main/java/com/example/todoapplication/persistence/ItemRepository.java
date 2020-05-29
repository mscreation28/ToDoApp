package com.example.todoapplication.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.todoapplication.async.DeleteAsyncTask;
import com.example.todoapplication.async.InsertAsyncTask;
import com.example.todoapplication.async.UpdateAsyncTask;
import com.example.todoapplication.models.Item;

import java.util.List;

public class ItemRepository {

    private ItemDatabase mItemDatabase;

    public ItemRepository(Context context) {
        mItemDatabase =ItemDatabase.getInstance(context);
    }

    public void insertItem(Item item) {
        new InsertAsyncTask(mItemDatabase.getItemDao()).execute(item);
    }

    public void updateItem(Item item) {
        new UpdateAsyncTask(mItemDatabase.getItemDao()).execute(item);
    }

    public void deleteItem(Item item) {
        new DeleteAsyncTask(mItemDatabase.getItemDao()).execute(item);
    }

    public LiveData<List<Item>> retriveToDo() {
        return mItemDatabase.getItemDao().getTodoList();
    }

    public LiveData<List<Item>> retriveDone() {
        return mItemDatabase.getItemDao().getDoneList();
    }
}
