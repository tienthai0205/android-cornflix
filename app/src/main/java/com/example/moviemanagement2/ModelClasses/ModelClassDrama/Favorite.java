package com.example.moviemanagement2.ModelClasses.ModelClassDrama;

import android.util.Log;

import java.util.ArrayList;

public class Favorite {
    public static ArrayList<MovieObject> movieObjectsInFavourite;


    public Favorite() {
        this.movieObjectsInFavourite = new ArrayList<MovieObject>();
    }
    static  {
            movieObjectsInFavourite = new ArrayList<>();

            for (int i = 0; i < DataProvider.movieObjects.size(); i++) {
                if (DataProvider.movieObjects.get(i).isFavorite() == true) {
                    movieObjectsInFavourite.add(DataProvider.movieObjects.get(i));
                }
            }
    }
}
