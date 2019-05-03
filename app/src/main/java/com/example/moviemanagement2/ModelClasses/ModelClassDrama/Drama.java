package com.example.moviemanagement2.ModelClasses.ModelClassDrama;

import android.os.Parcel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Drama extends  MovieObject {

    private String description;
    private String productionCountry;
    private ArrayList<Episodes> episodes;

    public Drama(String title, String genre, String releaseYear, String director,
                 String starring, boolean isFavourite, String description, String productionCountry) {

        super(title, genre, releaseYear, director, starring, isFavourite);
        this.description = description;
        this.productionCountry = productionCountry;
        episodes = new ArrayList<>();
    }

    public Drama(String title, String genre, String releaseYear, String director, String starring, String description, String productionCountry, ArrayList<Episodes> episodes) {
        super(title, genre, releaseYear, director, starring);
        this.description = description;
        this.productionCountry = productionCountry;
        this.episodes = episodes;
    }

    public Drama(String title, String genre, String releaseYear, String director, String starring, boolean isFavourite, String photoFileName, String description, String productionCountry) {
        super(title, genre, releaseYear, director, starring, isFavourite, photoFileName);
        this.description = description;
        this.productionCountry = productionCountry;
        episodes = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
    }

    public void addEpisode(String epName, String epDuration, double epRate, Drama drama) {
        Log.d("TIEN", episodes.toString());

        Episodes episode = new Episodes(epName, epDuration, epRate, drama);
        episodes.add(episode);
    }

    public List<Episodes> getEpisodes() {
        return episodes;
    }

}