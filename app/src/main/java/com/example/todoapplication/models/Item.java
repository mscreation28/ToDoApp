package com.example.todoapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "itemname")
    private String item;

    @ColumnInfo(name = "timestamp")
    private String timestamp;

    @ColumnInfo(name = "done",defaultValue = "0")
    private int done;

    @Ignore
    public Item() {
    }

    public Item(int id, String item, String timestamp,int done) {
        this.id = id;
        this.item = item;
        this.timestamp = timestamp;
        this.done = done;
    }


    protected Item(Parcel in) {
        id = in.readInt();
        item = in.readString();
        timestamp = in.readString();
        done = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int isDone() {
        return done;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", done=" + done +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(item);
        dest.writeString(timestamp);
        dest.writeInt(done);
    }
}
