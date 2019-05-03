package com.example.moviemanagement2.MainMenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.moviemanagement2.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openFavourite(View view) {

        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }

    public void openMovieLibrary(View view) {
        Intent intent = new Intent(this, LibraryMovieActivity.class);
        startActivity(intent);
    }
}
