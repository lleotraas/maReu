package com.lamzone.mareu.injector;

import com.lamzone.mareu.service.DummyReunionApiService;
import com.lamzone.mareu.service.ReunionApiService;

/**
 * Created by lleotraas on 04.
 */
public class DependencyInjector {

    private static ReunionApiService service = new DummyReunionApiService();

    public static ReunionApiService getReunionApiService(){ return service;}
}
