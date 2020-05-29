package com.example.todoapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.R;
import com.example.todoapplication.models.Item;
import com.example.todoapplication.persistence.ItemRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder2> {

    private static final String TAG = "ToDoAdapter";
    private ArrayList<Item> mItems = new ArrayList<>();

    private ItemRepository mItemRepository;
    private CoordinatorLayout mCoordinatorLayout;

    private OnTaskListner mOnTaskListner;

    public ToDoAdapter(ArrayList<Item> mItems,OnTaskListner onTaskListner, ItemRepository itemRepository, CoordinatorLayout coordinatorLayout) {
        Log.d(TAG, "ToDoAdapter: counstructor");
        mItemRepository = itemRepository;
        mCoordinatorLayout = coordinatorLayout;
        mOnTaskListner = onTaskListner;
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ToDoAdapter.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        return new ViewHolder2(view,mOnTaskListner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        holder.item.setText(mItems.get(position).getItem());
        holder.chbx.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView item;
        private CheckBox chbx;
        OnTaskListner onTaskListner;

        private ViewHolder2(@NonNull View itemView, OnTaskListner onTaskListner) {
            super(itemView);
            item = itemView.findViewById(R.id.item_name);
            chbx = itemView.findViewById(R.id.chbox);
            this.onTaskListner = onTaskListner;
            itemView.setOnClickListener(this);
//
            chbx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onCheckedChanged: Doneeee" + mItems.get(getAdapterPosition()).getItem());
                    final Item finalItem2 = new Item();
                    finalItem2.setItem(mItems.get(getAdapterPosition()).getItem());
                    finalItem2.setId(mItems.get(getAdapterPosition()).getId());
                    finalItem2.setItem(mItems.get(getAdapterPosition()).getItem());
                    finalItem2.setTimestamp(mItems.get(getAdapterPosition()).getTimestamp());
                    finalItem2.setDone(1);
                    mItemRepository.updateItem(finalItem2);

                    Snackbar snackbar = Snackbar
                            .make(mCoordinatorLayout, " MOVED TO DONE..! ",Snackbar.LENGTH_LONG);
                    snackbar.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finalItem2.setDone(0);
                            mItemRepository.updateItem(finalItem2);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                }
            });
            Log.d(TAG, "ViewHolder: item view todo");
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: Hii ");
            onTaskListner.OnTaskClick(getAdapterPosition());
        }
    }

    public interface OnTaskListner {
        void OnTaskClick(int position);
    }
}
