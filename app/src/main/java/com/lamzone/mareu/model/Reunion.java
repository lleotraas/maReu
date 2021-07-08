package com.lamzone.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public class Reunion implements Parcelable {
    private long mId;
    private String mTime;
    private String mRoom;
    private String mName;
    private List<String>mMembers;

    public Reunion(long id, String room, String time, String name, List<String> members) {
        mId = id;
        mTime = time;
        mRoom = room;
        mName = name;
        mMembers = members;
    }

    protected Reunion(Parcel in) {
        mId = in.readLong();
        mTime = in.readString();
        mRoom = in.readString();
        mName = in.readString();
        mMembers = in.createStringArrayList();
    }

    public static final Creator<Reunion> CREATOR = new Creator<Reunion>() {
        @Override
        public Reunion createFromParcel(Parcel in) {
            return new Reunion(in);
        }

        @Override
        public Reunion[] newArray(int size) {
            return new Reunion[size];
        }
    };

    public long getId() {
        return mId;
    }

    public String getTime() {
        return mTime;
    }

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
        dest.writeLong(mId);
        dest.writeString(mTime);
        dest.writeString(mRoom);
        dest.writeString(mName);
        dest.writeStringList(mMembers);
    }
}
