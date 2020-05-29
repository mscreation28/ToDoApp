package com.example.todoapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.todoapplication.fragment.ToDoFrag;
import com.example.todoapplication.models.Item;
import com.example.todoapplication.persistence.ItemRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity implements
        View.OnClickListener,
        TextWatcher{

    private static final String TAG = "EditView";

    private EditText mEditText;
    private ImageButton mBackBtn;
    private FloatingActionButton mDonefab;
    private ImageButton mDelBtn;

    private Item mFinalItem;
    private Item mInitialTtem;

    private ItemRepository mItemRepository;

    private InputMethodManager mImm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        mBackBtn = (ImageButton) findViewById(R.id.toolbar_back);
        mEditText = (EditText) findViewById(R.id.edit_task_text);
        mDelBtn = (ImageButton) findViewById(R.id.delete_task);
        mDonefab = (FloatingActionButton) findViewById(R.id.fab_edit);
        mImm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        mItemRepository = new ItemRepository(this);

        getIncomingIntent();

        setEditText();

        setistner();
    }

    private void getIncomingIntent() {
        mInitialTtem = getIntent().getParcelableExtra("task");
        mFinalItem = new Item();
        mFinalItem.setItem(mInitialTtem.getItem());
        mFinalItem.setTimestamp(mInitialTtem.getTimestamp());
        mFinalItem.setDone(mInitialTtem.getDone());
        mFinalItem.setId(mInitialTtem.getId());
    }

    private void setEditText() {
        mEditText.setText(mInitialTtem.getItem());
        mEditText.setSelection(mEditText.length());
        mEditText.requestFocus();
//        mImm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
        
        mEditText.requestFocus();
        mEditText.setFocusedByDefault(true);
        mImm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        Log.d(TAG, "setEditText: focus");
    }

    private void setistner() {
        mEditText.setKeyListener(new EditText(this).getKeyListener());
        mDelBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mDonefab.setOnClickListener(this);
        mEditText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.delete_task:{
                mItemRepository.deleteItem(mInitialTtem);
                hideSoftKeyboard();
                finish();
                break;
            }
            case  R.id.toolbar_back:{
                hideSoftKeyboard();
                finish();
                break;
            }

            case R.id.fab_edit: {
                String str = mEditText.getText().toString();
                mFinalItem.setItem(str);
                mItemRepository.updateItem(mFinalItem);
                hideSoftKeyboard();
                finish();
                break;
            }
        }
    }

    private void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if(view == null) {
            view = new View(this);
        }
        mImm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, "onTextChanged: "+s);
        if(s.length()==0) {
            mDonefab.setEnabled(false);
            mDonefab.setAlpha(0.25f);
        }
        else
        {
            mDonefab.setAlpha(1f);
            mDonefab.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
