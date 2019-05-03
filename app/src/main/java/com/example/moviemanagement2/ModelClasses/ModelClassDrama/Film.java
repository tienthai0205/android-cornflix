package com.example.moviemanagement2.ModelClasses.ModelClassDrama;



public class Film extends MovieObject {

    private String duration;
    private boolean isNominated;

    public Film(String title, String genre, String releaseYear,
                String director, String starring,boolean isFavourite,String image, String duration, boolean isNominated) {
        super(title, genre, releaseYear, director, starring, isFavourite, image);
        this.duration = duration;
        this.isNominated = isNominated;
    }

    public Film(String title, String genre, String releaseYear, String director, String starring, String duration, boolean isNominated) {
        super(title, genre, releaseYear, director, starring);
        this.duration = duration;
        this.isNominated = isNominated;
    }
    public Film(String title, String genre, String releaseYear, String director, String starring,boolean isFavorite, String duration, boolean isNominated) {
        super(title, genre, releaseYear, director, starring, isFavorite);
        this.duration = duration;
        this.isNominated = isNominated;
    }

    public Film(String title, String genre, String releaseYear, String director, String starring) {
        super(title, genre, releaseYear, director, starring);
    }

//    public Film(String title, String genre, String releaseYear, String director, String starring, String duration, boolean isNominated,String photoFileName) {
//        super(title, genre, releaseYear, director, starring);
//        this.duration = duration;
//        this.isNominated = isNominated;
//    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isNominated() {
        return isNominated;
    }

    public void setNominated(boolean nominated) {
        isNominated = nominated;
    }


}
