package com.lamzone.mareu.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
@Entity
public class Meeting implements Parcelable {

    @PrimaryKey
    private final long mId;

    private int mColor;
    private String mHour;
    private String mMinute;
    private String mRoom;
    private String mName;

    public Meeting(long id, int color, String hour, String minute, String room, String name) {
        mId = id;
        mColor = color;
        mHour = hour;
        mMinute = minute;
        mRoom = room;
        mName = name;
    }

    protected Meeting(Parcel in) {
        mId = in.readLong();
        mColor = in.readInt();
        mHour = in.readString();
        mMinute = in.readString();
        mRoom = in.readString();
        mName = in.readString();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    // --- GETTER ---
    public long getId() { return mId; }
    public int getColor() {
        return mColor;
    }
    public String getHour() {
        return mHour;
    }
    public String getMinute(){ return mMinute; }
    public String getRoom() {
        return mRoom;
    }
    public String getName() {
        return mName;
    }

    // --- SETTER ---
    public void setColor(int color) {
        mColor = color;
    }
    public void setHour(String hour) {
        mHour = hour;
    }
    public void setMinute(String minute) {
        mMinute = minute;
    }
    public void setRoom(String room) {
        mRoom = room;
    }
    public void setName(String name) {
        mName = name;
    }

//    @Override
//    public String toString() {
//        String members = "";
//        for (String string:mMembers) {
//            members += string + ", ";
//        }
//        return members;
//    }
//
//    public String toStringDetail() {
//        String members = "";
//        for (String string:mMembers) {
//            members += string + "\n";
//        }
//        return members;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mColor);
        dest.writeString(mHour);
        dest.writeString(mMinute);
        dest.writeString(mRoom);
        dest.writeString(mName);
    }

}
