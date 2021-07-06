package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Reunion;

import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public class DummyReunionApiService implements ReunionApiService{

    private List<Reunion> mReunions = DummyReunion.generateReunion();

    @Override
    public List<Reunion> getReunion() {
        return mReunions;
    }

    @Override
    public void removeReunion(Reunion reunion) {

    }

    @Override
    public void addReunion() {

    }

    @Override
    public void sorting() {

    }

    @Override
    public String makeDateString(int day, int month, int year) {
        return String.format("%02d/%02d/%4d",day, month, year);
    }

    @Override
    public String makeHourString(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }
}
