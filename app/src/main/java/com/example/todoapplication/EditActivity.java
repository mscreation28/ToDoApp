package com.example.todoapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {

    private static final String TAG = "EditView";

    private EditText mEditText;
    private ImageButton mBackBtn;
    private FloatingActionButton mDonefab;
    private ImageButton mDelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        mBackBtn = (ImageButton) findViewById(R.id.toolbar_back);
        mEditText = (EditText) findViewById(R.id.edit_task_text);
        mDelBtn = (ImageButton) findViewById(R.id.delete_task);
        mDonefab = (FloatingActionButton) findViewById(R.id.fab_edit);



    }
}
