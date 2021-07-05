package com.lamzone.mareu.model;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public class Reunion {
    private String mRoom;
    private String mHour;
    private String mName;
    private List<String>mMembers;

    public Reunion(String room, String hour, String name, List<String> members) {
        mRoom = room;
        mHour = hour;
        mName = name;
        mMembers = members;
    }

    public String getRoom() {
        return mRoom;
    }

    public String getHour() {
        return mHour;
    }

    public String getName() {
        return mName;
    }

    public List<String> getMembers() {
        return mMembers;
    }
}
