package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todoapplication.adapter.ToDoAdapter;
import com.example.todoapplication.models.Item;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        TabLayout.BaseOnTabSelectedListener{

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;

    private ArrayList<Item> mItems = new ArrayList<>();
    private ToDoAdapter mToDoAdapter;
    private CoordinatorLayout mCoordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =(RecyclerView)findViewById(R.id.recycle_view);
        mCoordinatorLayout = findViewById(R.id.coordinator_layout);

        initRecyclerView();
        insertFackItem();

        setSupportActionBar((androidx.appcompat.widget.Toolbar)findViewById(R.id.app_titlebar));
        setTitle("ToDo List");
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mToDoAdapter = new ToDoAdapter(mItems);

        mRecyclerView.setAdapter(mToDoAdapter);
    }

    private void insertFackItem() {
        for (int i=1; i<=15 ;i++) {
            Item item = new Item();
            item.setItem("Title # :" + i);
            item.setTimestamp("Jan 2020");
            mItems.add(item);
        }
        Log.d(TAG, "insertFackItem: items added");
        mToDoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: Hello buddy");
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.d(TAG, "onTabSelected: dwdwdw");
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
