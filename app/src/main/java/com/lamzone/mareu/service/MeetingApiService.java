package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public interface MeetingApiService {

    List<Meeting> getMeeting();

    void removeMeeting(Meeting meeting);

    void addMeeting(Meeting meeting);

    String makeHourString(int hour, int minute);

    int getImageColor();
}
