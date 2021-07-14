package com.lamzone.mareu;

import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.DummyReunion;
import com.lamzone.mareu.service.ReunionApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ReunionApiService service;

    @Before
    public void setup(){
        service = DependencyInjector.getReunionApiService();
        Reunion reunionTest = new Reunion(-12345678, "14", "15", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Reunion reunionTest1 = new Reunion(-12345678, "10", "30", "J", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Reunion reunionTest2 = new Reunion(-12345678, "14", "45", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Reunion reunionTest3 = new Reunion(-12345678, "10", "00", "J", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Reunion reunionTest4 = new Reunion(-12345678, "14", "15", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        service.getReunion().add(reunionTest);
        service.getReunion().add(reunionTest1);
        service.getReunion().add(reunionTest2);
        service.getReunion().add(reunionTest3);
        service.getReunion().add(reunionTest4);
    }
    @After
    public void finish(){
        service.getReunion().removeAll(service.getReunion());
    }

    @Test
    public void getReunionListWithSuccess() {
        //ARRANGE

        //ACT

        //ASSERT
        assertEquals(5, service.getReunion().size());
       // assertEquals();
    }

    @Test
    public void deleteReunionWithSuccess(){
        //ARRANGE
        Reunion reunionToDelete = service.getReunion().get(0);
        //ACT
        service.removeReunion(reunionToDelete);
        //ASSERT
        assertFalse(service.getReunion().contains(reunionToDelete));
    }

    @Test
    public void sortingByTimeWithSuccess(){
        //ARRANGE

        //ACT
        List<Reunion> sortingExpected = service.sortingByTime(14);
        //ASSERT
        assertEquals(3, sortingExpected.size());
    }

    @Test
    public void sortingByRoomWithSuccess(){
        //ARRANGE

        //ACT
        List<Reunion> sortingExpected = service.sortingByRoom("J");
        //ASSERT
        assertEquals(2, sortingExpected.size());
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