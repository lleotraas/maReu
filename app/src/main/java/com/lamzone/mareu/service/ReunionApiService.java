package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Reunion;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public interface ReunionApiService {

    List<Reunion> getReunion();

    void removeReunion(Reunion reunion);

    void addReunion();

    void sorting();

    String makeDateString(int day, int month, int year);

    String makeHourString(int hour, int minute);
}
