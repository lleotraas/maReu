package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public abstract class DummyMeeting {

    public static List<Meeting> DUMMY_MEETING = new ArrayList<>();
    static List<Meeting> generateMeeting(){ return new ArrayList<>(DUMMY_MEETING);}

    public static List<Member> DUMMY_MEMBER = new ArrayList<>();
    static List<Member> generateMember(){ return new ArrayList<>(DUMMY_MEMBER); }
}
