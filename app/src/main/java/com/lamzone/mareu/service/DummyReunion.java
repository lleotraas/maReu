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
            new Reunion(1,"19","30","J","luigi",Arrays.asList("luigi@lamzone.com", "Peach@lamzone.com", "mario@lamzone.com", "Toad@lamzonne.com")),
            new Reunion(2, "08", "15", "I", "Peach", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(3,"18", "45", "H", "D.K.", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(4,"09", "00", "G", "Lakitu", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(5,"17", "15", "F","Goomba", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(6,"10","30","E","Diddy",Arrays.asList("luigi@lamzone.com", "Peach@lamzone.com", "mario@lamzone.com", "Toad@lamzonne.com")),
            new Reunion(7, "16", "45", "D", "Daisy", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(8,"11", "00", "C", "Mario", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(9,"15", "15", "B", "Troopa", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(10,"14", "30", "A","Paratroopa", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(11,"14","45","J","Waluigi",Arrays.asList("luigi@lamzone.com", "Peach@lamzone.com", "mario@lamzone.com", "Toad@lamzonne.com")),
            new Reunion(12, "15", "00", "I", "Wario", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(13,"11", "15", "H", "Yoshi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(14,"16", "30", "G", "Boo", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(15,"10", "45", "F","Bowser", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(16,"17","00","E","King.K.Rool",Arrays.asList("luigi@lamzone.com", "Peach@lamzone.com", "mario@lamzone.com", "Toad@lamzonne.com")),
            new Reunion(17, "09", "15", "D", "Dixie", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(18,"18", "30", "C", "Link", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(19,"08", "45", "B", "Zelda", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
            new Reunion(20,"19", "00", "A","Pikachu", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com"))
    );

    static List<Reunion> generateReunion(){ return new ArrayList<>(DUMMY_REUNION);}
}
