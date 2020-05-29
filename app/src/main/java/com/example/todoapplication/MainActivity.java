package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.todoapplication.adapter.ToDoAdapter;
import com.example.todoapplication.fragment.DoneFrag;
import com.example.todoapplication.fragment.ToDoFrag;
import com.example.todoapplication.fragment.ViewPagerAdapter;
import com.example.todoapplication.models.Item;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private InputMethodManager mImm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mImm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);

        //Add Fragment
        mViewPagerAdapter.addFrag(new ToDoFrag(this),"ToDo");
        mViewPagerAdapter.addFrag(new DoneFrag(),"Done");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        setSupportActionBar((androidx.appcompat.widget.Toolbar)findViewById(R.id.app_titlebar));
        setTitle("ToDo List");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: clickedd");
        super.onBackPressed();
//        ToDoFrag.onBackPressed();
    }


}
