package com.example.todoapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.R;
import com.example.todoapplication.models.Item;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private static final String TAG = "ToDoAdapter";
    private ArrayList<Item> mItems = new ArrayList<>();

    public ToDoAdapter(ArrayList<Item> mItems) {
        Log.d(TAG, "ToDoAdapter: counstructor");
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(mItems.get(position).getItem());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(this);
            Log.d(TAG, "ViewHolder: item view");
        }


        @Override
        public void onClick(View v) {
            v.setBackgroundColor(Color.GRAY);
            Log.d(TAG, "onClick: Hii");
        }
    }
}
