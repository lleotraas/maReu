package com.lamzone.mareu.repositories;

import androidx.lifecycle.LiveData;

import com.lamzone.mareu.database.dao.MeetingDao;
import com.lamzone.mareu.model.Meeting;

/**
 * Created by lleotraas on 14.
 */
public class MeetingDataRepository {

    private final MeetingDao mMeetingDao;

    public MeetingDataRepository(MeetingDao meetingDao) {
        mMeetingDao = meetingDao;
    }

    // --- GET MEETING ---
    public LiveData<Meeting> getMeeting(long meetingId) {
        return this.mMeetingDao.getMeeting(meetingId);
    }
}
