package com.lamzone.mareu.service;

import android.graphics.Color;

import com.lamzone.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lleotraas on 04.
 */
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetings = DummyMeeting.generateMeeting();

    @Override
    public List<Meeting> getMeeting() {
        return mMeetings;
    }

    @Override
    public void removeMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    /**
     * Return the hour string format for the UI
     * @param hour
     * @param minute
     * @return
     */
    @Override
    public String makeHourString(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }

    /**
     * Return a random color for the meeting image
     * @return
     */
    @Override
    public int getImageColor() {
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        return color;
    }
}
