package com.lamzone.mareu.service;

import android.graphics.Color;

import com.lamzone.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        mReunions.remove(reunion);
    }

    @Override
    public void addReunion(Reunion reunion) {
        mReunions.add(reunion);
    }

    @Override
    public List<Reunion> sortingByTime(int hour) {
        List<Reunion> reunions = new ArrayList<>();
        String hourString = String.format("%02d", hour);
        for (int index = 0;index < mReunions.size();index++){
            if (mReunions.get(index).getHour().contains(hourString)){
                reunions.add(mReunions.get(index));
            }
        }
        return  reunions;
    }

    @Override
    public List<Reunion> sortingByRoom(String room) {
        List<Reunion> reunions = new ArrayList<>();
        for (int index = 0;index < mReunions.size();index++){
            if (mReunions.get(index).getRoom().contains(room)){
                reunions.add(mReunions.get(index));
            }
        }
        return reunions;
    }

    @Override
    public String makeHourString(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }

    @Override
    public int getImageColor() {
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        return color;
    }
}
