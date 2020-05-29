package com.example.todoapplication.async;

import android.os.AsyncTask;

import com.example.todoapplication.models.Item;
import com.example.todoapplication.persistence.ItemDao;

public class DeleteAsyncTask extends AsyncTask<Item,Void,Void> {

    private ItemDao mItemDao;

    public DeleteAsyncTask(ItemDao mItemDao) {
        this.mItemDao = mItemDao;
    }

    @Override
    protected Void doInBackground(Item... items) {
        mItemDao.delete(items);
        return null;
    }
}
