package com.example.todoapplication.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.R;
import com.example.todoapplication.models.Item;
import com.example.todoapplication.persistence.ItemRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DoneAdapter extends RecyclerView.Adapter<DoneAdapter.ViewHolder> {

    private static final String TAG = "DoneAdapter";

    private ArrayList<Item> mItems = new ArrayList<>();
    private ItemRepository mItemRepository;
    private CoordinatorLayout mCoordinatorLayout;
    private Item finalItem2 ;

    public DoneAdapter(ArrayList<Item> mItems,ItemRepository itemRepository,CoordinatorLayout coordinatorLayout) {
        this.mItems = mItems;
        mItemRepository = itemRepository;
        mCoordinatorLayout = coordinatorLayout;
    }

    @NonNull
    @Override
    public DoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.done_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(mItems.get(position).getItem());
        holder.item.setPaintFlags(holder.item.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.chbx.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView item;
        private CheckBox chbx;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_name);
            chbx = itemView.findViewById(R.id.chbox2);
            itemView.setOnClickListener(this);
//
            chbx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onCheckedChanged: Doneeee" + mItems.get(getAdapterPosition()).getItem());
                    finalItem2 = new Item();
                    finalItem2.setItem(mItems.get(getAdapterPosition()).getItem());
                    finalItem2.setId(mItems.get(getAdapterPosition()).getId());
                    finalItem2.setItem(mItems.get(getAdapterPosition()).getItem());
                    finalItem2.setTimestamp(mItems.get(getAdapterPosition()).getTimestamp());
                    finalItem2.setDone(0);
                    mItemRepository.updateItem(finalItem2);

                    Snackbar snackbar = Snackbar
                            .make(mCoordinatorLayout, " MOVED TO TODO..! ",Snackbar.LENGTH_LONG);
                    snackbar.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finalItem2.setDone(1);
                            mItemRepository.updateItem(finalItem2);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                }
            });
            Log.d(TAG, "ViewHolder: item view done");
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: Hii");
        }


    }
}
