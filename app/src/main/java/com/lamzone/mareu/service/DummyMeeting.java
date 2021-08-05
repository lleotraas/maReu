package com.lamzone.mareu.service;

import com.lamzone.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lleotraas on 04.
 */
public abstract class DummyMeeting {
    public static List<Meeting> DUMMY_MEETING =
            new ArrayList<Meeting>();
//    Arrays.asList(
//            new Meeting(-5123471,"19","30","J","luigi",Arrays.asList("luigi@lamzone.com", "Peach@lamzone.com", "mario@lamzone.com", "Toad@lamzonne.com")),
//            new Meeting(-27562457, "08", "15", "I", "Peach", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-8453213,"18", "45", "H", "D.K.", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-32165474,"09", "00", "G", "Lakitu", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-6844645,"17", "15", "F","Goomba", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-39752396,"10","30","E","Diddy",Arrays.asList("luigi@lamzone.com", "Peach@lamzone.com", "mario@lamzone.com", "Toad@lamzonne.com")),
//            new Meeting(-8344897, "16", "45", "D", "Daisy", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-38798138,"11", "00", "C", "Mario", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-8964809,"15", "15", "B", "Troopa", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-89721010,"14", "30", "A","Paratroopa", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-8468811,"14","45","J","Waluigi",Arrays.asList("luigi@lamzone.com", "Peach@lamzone.com", "mario@lamzone.com", "Toad@lamzonne.com")),
//            new Meeting(-17359812, "15", "00", "I", "Wario", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-8462313,"11", "15", "H", "Yoshi", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-84116714,"16", "30", "G", "Boo", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-7891215,"10", "45", "F","Bowser", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-84321516,"17","00","E","King.K.Rool",Arrays.asList("luigi@lamzone.com", "Peach@lamzone.com", "mario@lamzone.com", "Toad@lamzonne.com")),
//            new Meeting(-7893217, "09", "15", "D", "Dixie", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-98713718,"18", "30", "C", "Link", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-5646319,"08", "45", "B", "Zelda", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com")),
//            new Meeting(-98772320,"19", "00", "A","Pikachu", Arrays.asList("luigi@lamzone.com", "peach@lamzone.com", "mario@lamzone.com", "toad@lamzone.com"))
//    );

    static List<Meeting> generateMeeting(){ return new ArrayList<>(DUMMY_MEETING);}
}
