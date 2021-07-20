package com.lamzone.mareu.injector;

import com.lamzone.mareu.service.DummyMeetingApiService;
import com.lamzone.mareu.service.MeetingApiService;

/**
 * Created by lleotraas on 04.
 */
public class DependencyInjector {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getReunionApiService(){ return service;}
}
