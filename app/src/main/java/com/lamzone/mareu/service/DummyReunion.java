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
            new Reunion("Reunion B", " - 18H00 - ", "Luigi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion C", " - 12H00 - ", "Peach", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion A", " - 14H30 - ", "D.K.", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion B", " - 15H00 - ", "Lakitu", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion C", " - 13H30 - ", "Goomba", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion A", " - 14H45 - ", "Toad", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion B", " - 09H30 - ", "Mario", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion C", " - 19H50 - ", "Waluigi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion A", " - 10H00 - ", "Bowser", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion B", " - 18H00 - ", "Luigi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion C", " - 12H00 - ", "Peach", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion A", " - 14H30 - ", "D.K.", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion B", " - 15H00 - ", "Lakitu", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion C", " - 13H30 - ", "Goomba", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion A", " - 14H45 - ", "Toad", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion B", " - 09H30 - ", "Mario", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion C", " - 19H50 - ", "Waluigi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion("Reunion A", " - 10H00 - ", "Bowser", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com"))
    );

    static List<Reunion> generateReunion(){ return new ArrayList<>(DUMMY_REUNION);}
}
