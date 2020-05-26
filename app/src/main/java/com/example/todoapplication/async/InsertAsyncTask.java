package com.example.todoapplication.async;

import android.os.AsyncTask;

import com.example.todoapplication.models.Item;
import com.example.todoapplication.persistence.ItemDao;

public class InsertAsyncTask extends AsyncTask<Item,Void,Void> {

    private ItemDao mItemDao;

    public InsertAsyncTask(ItemDao mItemDao) {
        this.mItemDao = mItemDao;
    }

    @Override
    protected Void doInBackground(Item... items) {
        mItemDao.insertItem(items);
        return null;
    }
}
