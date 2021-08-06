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
    private List<Meeting> mMeetingListfiltered = new ArrayList<>(mMeetings);

    @Override
    public List<Meeting> getMeeting() {
        return mMeetings;
    }

    @Override
    public List<Meeting> getMeetingListFiltered() {
        return mMeetingListfiltered;
    }

    @Override
    public void removeMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void removeMeetingListFiltered(Meeting meeting) {
        mMeetingListfiltered.remove(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public List<Meeting> meetingFilter(CharSequence constraint) {
        List<Meeting> filteredList = new ArrayList<>();
        if (constraint == null || constraint.length() == 0){
            filteredList.addAll(mMeetingListfiltered);
        }else{
            String filteredPattern = constraint.toString().toUpperCase().trim();
            for (Meeting meeting: mMeetingListfiltered) {
                if(meeting.getHour().toUpperCase().contains(filteredPattern)){
                    filteredList.add(meeting);
                }
                if(meeting.getRoom().toUpperCase().contains(filteredPattern)){
                    filteredList.add(meeting);
                }
            }
        }
        return filteredList;
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
