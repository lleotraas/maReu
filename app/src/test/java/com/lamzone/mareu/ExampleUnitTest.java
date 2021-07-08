package com.lamzone.mareu;

import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.DummyReunion;
import com.lamzone.mareu.service.ReunionApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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
        Reunion reunionTest = new Reunion(123,"A","10h50","reunion", Arrays.asList("un", "deux", "trois", "quatre"));
        DummyReunion.DUMMY_REUNION.add(reunionTest);
//        service.addReunion(new Reunion(123,"A","10h50","reunion", Arrays.asList("un", "deux", "trois", "quatre")));
    }

    @Test
    public void getReunionListWithSuccess() {
        //ARRANGE

        //ACT

        //ASSERT
        assertEquals(19, service.getReunion().size());
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
}