package com.lamzone.mareu.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lleotraas on 12.
 */
@Entity(foreignKeys = @ForeignKey(entity = Meeting.class,
        parentColumns = "mId",
        childColumns = "mMeetingId"))
public class Member {
    @PrimaryKey(autoGenerate = true)
    private long mId;
    private String mMember;
    private long mMeetingId;



    public Member(long id, String member, long meetingId) {
        mId = id;
        mMember = member;
        mMeetingId = meetingId;
    }

    public long getId() {
        return mId;
    }

    public String getMember() {
        return mMember;
    }

    public long getMeetingId() {
        return mMeetingId;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setMember(String member) {
        mMember = member;
    }

    public void setMeetingId(long meetingId) {
        mMeetingId = meetingId;
    }
}
