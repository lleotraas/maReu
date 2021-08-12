package com.lamzone.mareu.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import androidx.lifecycle.LiveData;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Member;

/**
 * Created by lleotraas on 12.
 */
@Dao
public interface MeetingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createMeeting(Meeting meeting);

    @Query("SELECT * FROM Meeting WHERE mId = :meetingId")
    LiveData<Meeting> getMeeting(long meetingId);
}
