package com.example.todoapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.R;
import com.example.todoapplication.adapter.ToDoAdapter;
import com.example.todoapplication.models.Item;

import java.util.ArrayList;

public class ToDoFrag extends Fragment {

    private static final String TAG = "ToDoFrag";

    private RecyclerView mRecyclerView;

    private ArrayList<Item> mItems = new ArrayList<>();
    private ToDoAdapter mToDoAdapter;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertFackItem();
    }

    View v;
    public ToDoFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.todo_layout,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);
        mCoordinatorLayout = v.findViewById(R.id.coordinator_layout);
        initRecyclerView();
        return v;
    }

    private void insertFackItem() {
        for (int i=1; i<=5 ;i++) {
            Item item = new Item();
            item.setItem("Title # :" + i);
            item.setTimestamp("Jan 2020");
            mItems.add(item);
        }
        Log.d(TAG, "insertFackItem: items added");

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mToDoAdapter = new ToDoAdapter(mItems);
        mRecyclerView.setAdapter(mToDoAdapter);
        mToDoAdapter.notifyDataSetChanged();
    }
}
