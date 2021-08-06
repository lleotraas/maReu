package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public interface MeetingApiService {

    List<Meeting> getMeeting();

    List<Meeting> getMeetingListFiltered();

    void removeMeeting(Meeting meeting);

    void removeMeetingListFiltered(Meeting meeting);

    void addMeeting(Meeting meeting);

    List<Meeting> meetingFilter(CharSequence constraint);

    String makeHourString(int hour, int minute);

    int getImageColor();
}
