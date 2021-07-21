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

    private List<Meeting> mMeetings = DummyMeeting.generateReunion();

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
     * Return a local list contains meetings with the hour in @param
     * @param hour
     * @return
     */
    @Override
    public List<Meeting> sortingByTime(int hour) {
        List<Meeting> meetings = new ArrayList<>();
        String hourString = String.format("%02d", hour);
        for (int index = 0; index < mMeetings.size(); index++){
            if (mMeetings.get(index).getHour().contains(hourString)){
                meetings.add(mMeetings.get(index));
            }
        }
        return meetings;
    }

    /**
     * Return a local list contains meetings with the room in @param
     * @param room
     * @return
     */
    @Override
    public List<Meeting> sortingByRoom(String room) {
        List<Meeting> meetings = new ArrayList<>();
        for (int index = 0; index < mMeetings.size(); index++){
            if (mMeetings.get(index).getRoom().contains(room)){
                meetings.add(mMeetings.get(index));
            }
        }
        return meetings;
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
