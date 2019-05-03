package com.example.moviemanagement2.MainMenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;


import com.example.moviemanagement2.Adapter.FavouriteMovieAdapter;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Favorite;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.MovieObject;
import com.example.moviemanagement2.R;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity  {
    GridView gridView;
    Button dots;
    private FavouriteMovieAdapter movieFavoriteAdapter;
    List<MovieObject>movieObjectsInFavourite = Favorite.movieObjectsInFavourite;

    public Context getContext(){
        return this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        gridView = findViewById(R.id.gridView);
        movieFavoriteAdapter = new FavouriteMovieAdapter(this, movieObjectsInFavourite);
        getSupportActionBar().setTitle(R.string.my_favourite);
        gridView.setAdapter(movieFavoriteAdapter);
        dots = findViewById(R.id.dotsFav);

    }


    @Override
    protected void onResume() {
        super.onResume();
        movieFavoriteAdapter.notifyDataSetChanged();
    }


}
