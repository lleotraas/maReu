package com.lamzone.mareu;

import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.service.MeetingApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ServiceTest {
    MeetingApiService service;

    @Before
    public void setup(){
        service = DependencyInjector.getMeetingApiService();
        Meeting meetingTest = new Meeting(-12345678, "14", "15", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Meeting meetingTest1 = new Meeting(-12345678, "10", "30", "J", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Meeting meetingTest2 = new Meeting(-12345678, "14", "45", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Meeting meetingTest3 = new Meeting(-12345678, "10", "00", "J", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Meeting meetingTest4 = new Meeting(-12345678, "14", "15", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        service.getMeeting().add(meetingTest);
        service.getMeeting().add(meetingTest1);
        service.getMeeting().add(meetingTest2);
        service.getMeeting().add(meetingTest3);
        service.getMeeting().add(meetingTest4);
    }
    @After
    public void finish(){
        service.getMeeting().removeAll(service.getMeeting());
        service = null;
    }

    @Test
    public void getReunionListWithSuccess() {
        //ARRANGE
        int listSizeExpected = 5;
        //ACT

        //ASSERT
        assertEquals(listSizeExpected, service.getMeeting().size());
       // assertEquals();
    }

    @Test
    public void deleteReunionWithSuccess(){
        //ARRANGE
        Meeting meetingToDelete = service.getMeeting().get(0);
        //ACT
        service.removeMeeting(meetingToDelete);
        //ASSERT
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void makeHourStringWithSuccess(){
        //ARRANGE
        int hour = 15;
        int minute = 45;
        String expectedHour = "15:45";
        //ACT
        String formatedHour = service.makeHourString(hour, minute);
        //ASSERT
        assertEquals(expectedHour, formatedHour);
    }
}