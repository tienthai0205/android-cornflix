package com.example.moviemanagement2.ModelClasses.ModelClassDrama;

import java.util.ArrayList;

public class DataProvider {

    public static ArrayList<MovieObject>movieObjects;

    public DataProvider(){
        this.movieObjects = new ArrayList<MovieObject>();

    }

    static {
        movieObjects = new ArrayList<>();

        Film theVow = new Film("The Vow", "Romance", "2012", "Michael Sucsy", "Rachel McAdams, Channing Tatum",true,"thevow.jpg", "104 minutes", true);
        Film rubySparks = new Film("Ruby Sparks", "Romance", "2012", "Valerie Faris, Jonathan Dayton","Zoe Kazan, Paul Dano",true,"rubysparks.jpg","105 minutes", false);
        Film titanic = new Film("Titanic", "Romance","1997", "James Cameron", "Kate Winslet, Leonardo Dicaprio",false,"titanic.jpg", "195 minutes", true);
        Film daysOfSummer = new Film("500 Days Of Summer", "Romance", "2009","Marc Webb","Joseph Gordon Levitt, Zooey Deschanel",false,"daysofsummer.jpg", "95 minutes", true );
        addFilm(theVow);
        addFilm(rubySparks);
        addFilm(titanic);
        addFilm(daysOfSummer);

        Drama theMummy = new Drama("The mummy", "Comedy", "1999", "me", "him",true, "mummy.jpg","cool", "usa");
        Drama twilight = new Drama("Twilight", "Romance", "2008", "me", "him",false,"twilight.jpg", "cool", "usa");
        addDrama(theMummy);
        addDrama(twilight);

        addEpToDrama("The Mummy 1", "2h4m",4.5 , theMummy);
        addEpToDrama("The Mummy 2: Tomb of the Dragon Emperor", "2h4m",4.5 , theMummy);
        addEpToDrama("The Mummy: Tomb of the Dragon Emperor", "2h4m",4.5 , theMummy);
        addEpToDrama("Twilight 1", "2h4m",4.5 , twilight);
        addEpToDrama("The Twilight Saga: New Moon (2009)", "2h4m",4.5 , twilight);
        addEpToDrama("The Twilight Saga: Eclipse (2010)", "2h4m",4.5 , twilight);
        addEpToDrama("The Twilight Saga: Breaking Dawn – Part 1 (2011)", "2h4m",4.5 , twilight);
        addEpToDrama("The Twilight Saga: Breaking Dawn – Part 2 (2012)", "2h4m",4.5 , twilight);


    }

    public static void addEpToDrama(String episodeName, String episodeDuration, double epRate, Drama drama){
        for (int i=0; i<movieObjects.size(); i ++) {
            if (movieObjects.get(i) instanceof Drama) {
                drama.addEpisode(episodeName,episodeDuration, epRate, drama);
                i++;
            }
        }
    }
    private static void addDrama(Drama drama){
        movieObjects.add(drama);
    }

    private static void addFilm(Film film){ movieObjects.add(film); }


}
