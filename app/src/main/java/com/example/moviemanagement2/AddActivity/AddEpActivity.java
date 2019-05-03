package com.example.moviemanagement2.AddActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.moviemanagement2.CompoundControl.RatingView;
import com.example.moviemanagement2.MainMenu.LibraryMovieActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Episodes;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.MovieObject;
import com.example.moviemanagement2.R;

public class AddEpActivity extends AppCompatActivity {

    EditText name, duration;
    RatingView rating;
    Drama drama;
    double ratingEp;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ep);
        position = getIntent().getIntExtra("keyDramaNamePosition", -1);

        drama = ((Drama) DataProvider.movieObjects.get(position));

        name = findViewById(R.id.addEpName);
        getSupportActionBar().setTitle("New Episode");
        duration = findViewById(R.id.addEpDuration);
        rating = findViewById(R.id.ratingBar2);

        Log.i("TienS", ratingEp+"");

    }

    public void onAddEpButtonClickk(View view) {
        String nameEp = name.getText().toString();
        String durationEp =  duration.getText().toString();
        drama.addEpisode(nameEp, durationEp, ratingEp, (Drama) drama);
        Intent intent = new Intent(this, LibraryMovieActivity.class);
        startActivity(intent);

    }

    public void onCancleButtonClick(View view) {
        finish();
    }

}
