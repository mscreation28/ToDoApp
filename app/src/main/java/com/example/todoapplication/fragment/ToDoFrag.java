package com.example.todoapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ToDoFrag extends Fragment{

    private static final String TAG = "ToDoFrag";

    private RecyclerView mRecyclerView;

    private ArrayList<Item> mItems = new ArrayList<>();
    private ToDoAdapter mToDoAdapter;
    private CoordinatorLayout mCoordinatorLayout;

    private EditText mEditText;

    private boolean enabled=false;

    private ItemRepository mItemRepository;

    private InputMethodManager mImm;
    private FloatingActionButton mFab;
    private LinearLayout mLinearLayout;
    private ImageButton mInsertButton;

    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemRepository = new ItemRepository(this.getContext());
//        insertFackItem();
        retriveToDo();
    }

    View v;
    public ToDoFrag(Context context) {
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.todo_layout,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);
        mLinearLayout = (LinearLayout) v.findViewById(R.id.text_edit_layout);
        mInsertButton = (ImageButton) v.findViewById(R.id.insert_todo);
        mImm = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightdiff = mRecyclerView.getRootView().getHeight() - mRecyclerView.getHeight();
                Log.d(TAG, "onGlobalLayout: "+heightdiff);
                if (heightdiff > 500) {
                    Log.d(TAG, "onGlobalLayout: keyboard open");
                    if(enabled == false) {
                        enabled = true;
                    }

                } else {
                    Log.d(TAG, "onGlobalLayout: keyboard closed");
                    if(enabled == true) {
                        disableContentInteraction();
                        enabled = false;
                    }
                }
            }
        });

        mCoordinatorLayout = v.findViewById(R.id.coordinator_layout_todo);
        mEditText = v.findViewById(R.id.new_todo);

        mFab=v.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mEditText = (EditText)v.findViewById(R.id.new_todo);
                mLinearLayout.setVisibility(View.VISIBLE);
                enableContentInteraction();
//                enabled = true;
                Log.d(TAG, "onClick: Hello");
            }
        });

        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtask = mEditText.getText().toString();
                Item item = new Item();
                item.setItem(newtask);
                item.setTimestamp(LocalDateTime.now().toString());
                if(!item.getItem().equals("")) {
                    mItemRepository.insertItem(item);
                }
                enabled = false;
                disableContentInteraction();
                hideSoftKeyboard();
                Log.d(TAG, "onClick: "+newtask+ LocalDateTime.now());
            }
        });

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



    private void enableContentInteraction() {
        mEditText.setKeyListener(new EditText(this.getContext()).getKeyListener());
        mEditText.requestFocus();
        mEditText.setFocusedByDefault(true);
//        mEditText.setFocusable(true);
//        mEditText.setFocusableInTouchMode(true);
        mEditText.setCursorVisible(true);
        mFab.setVisibility(View.GONE);
//        mEditText.setEnabled(true);
//        enabled = true;
        Log.d(TAG, "enableContentInteraction: ");
        mImm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
//        mImm.showSoftInput(getView(),InputMethodManager.SHOW_IMPLICIT);
        Log.d(TAG, "enableContentInteraction: "+mImm);
//        enabled = true;
    }

    private void disableContentInteraction() {

        mLinearLayout.setVisibility(View.GONE);
        mFab.setVisibility(View.VISIBLE);
        hideSoftKeyboard();
        mEditText.setText("");
        mFab.setEnabled(true);
//        enabled = false;
        Log.d(TAG, "disableContentInteraction: gyftft");
    }

    private void hideSoftKeyboard() {
//        View view = new View(mContext);
//        view =
        Log.d(TAG, "hideSoftKeyboard: hide keyboard");
        mImm.hideSoftInputFromWindow(getView().getRootView().getWindowToken(),0);
    }

}
