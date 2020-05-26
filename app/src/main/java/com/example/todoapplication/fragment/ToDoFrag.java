package com.example.todoapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.R;
import com.example.todoapplication.adapter.ToDoAdapter;
import com.example.todoapplication.models.Item;
import com.example.todoapplication.persistence.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class ToDoFrag extends Fragment {

    private static final String TAG = "ToDoFrag";

    private RecyclerView mRecyclerView;

    private ArrayList<Item> mItems = new ArrayList<>();
    private ToDoAdapter mToDoAdapter;
    private CoordinatorLayout mCoordinatorLayout;

    private ItemRepository mItemRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemRepository = new ItemRepository(this.getContext());


//        insertFackItem();
        retriveToDo();
    }

    View v;
    public ToDoFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.todo_layout,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);
        mCoordinatorLayout = v.findViewById(R.id.coordinator_layout_todo);

        initRecyclerView();
        return v;
    }


    private void insertFackItem() {
        for (int i=1; i<=10;i++) {
            Item item = new Item();
            item.setItem("Title # :" + i);
            item.setTimestamp("Jan 2020");
            mItemRepository.insertItem(item);
        }
        Log.d(TAG, "insertFackItem: items added");

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mToDoAdapter = new ToDoAdapter(mItems,mItemRepository,mCoordinatorLayout);
        mRecyclerView.setAdapter(mToDoAdapter);
        mToDoAdapter.notifyDataSetChanged();
    }

    private void retriveToDo() {
        mItemRepository.retriveToDo().observe(this,new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                if(mItems.size()>0) {
                    mItems.clear();
                }
                if(items != null) {
                    mItems.addAll(items);
                }
                mToDoAdapter.notifyDataSetChanged();
            }
        });
    }
}
