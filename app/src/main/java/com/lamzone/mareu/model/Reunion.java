package com.lamzone.mareu.model;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public class Reunion {
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
}
