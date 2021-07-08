package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public abstract class DummyReunion {
    public static List<Reunion>DUMMY_REUNION = Arrays.asList(
            new Reunion(1,"B", "18H00", "Luigi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(2, "C", "12H00", "Peach", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(3,"A", "14H30", "D.K.", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(4,"B", "15H00", "Lakitu", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(5,"C", "13H30", "Goomba", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(6,"A", "14H45", "Toad", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(7,"B", "09H30", "Mario", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(8,"C", "19H50", "Waluigi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(9,"A", "10H00", "Bowser", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(10,"B", "18H00", "Luigi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(11,"C", "12H00", "Peach", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(12,"A", "14H30", "D.K.", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(13,"B", "15H00", "Lakitu", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(14,"C", "13H30", "Goomba", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(15,"A", "14H45", "Toad", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(16,"B", "09H30", "Mario", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(17,"C", "19H50", "Waluigi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(18,"A", "10H00", "Bowser", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com"))
    );

    static List<Reunion> generateReunion(){ return new ArrayList<>(DUMMY_REUNION);}
}
