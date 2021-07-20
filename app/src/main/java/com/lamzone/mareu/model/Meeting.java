package com.lamzone.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public class Meeting implements Parcelable {
    private final int mColor;
    private final String mHour;
    private final String mMinute;
    private final String mRoom;
    private final String mName;
    private final List<String>mMembers;

    public Meeting(int color, String hour, String minute, String room, String name, List<String> members) {
        mColor = color;
        mHour = hour;
        mMinute = minute;
        mRoom = room;
        mName = name;
        mMembers = members;
    }

    protected Meeting(Parcel in) {
        mColor = in.readInt();
        mHour = in.readString();
        mMinute = in.readString();
        mRoom = in.readString();
        mName = in.readString();
        mMembers = in.createStringArrayList();
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

    public List<String> getMembers() {
        return mMembers;
    }

    @Override
    public String toString() {
        String members = "";
        for (String string:mMembers) {
            members += string + ", ";
        }
        return members;
    }

    public String toStringDetail() {
        String members = "";
        for (String string:mMembers) {
            members += string + "\n";
        }
        return members;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mColor);
        dest.writeString(mHour);
        dest.writeString(mMinute);
        dest.writeString(mRoom);
        dest.writeString(mName);
        dest.writeStringList(mMembers);
    }
}
