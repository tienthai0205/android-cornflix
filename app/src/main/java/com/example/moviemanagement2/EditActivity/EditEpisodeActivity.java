package com.example.moviemanagement2.EditActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.moviemanagement2.CompoundControl.RatingView;
import com.example.moviemanagement2.MainMenu.LibraryEpisodeActivity;
import com.example.moviemanagement2.MainMenu.LibraryMovieActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Episodes;
import com.example.moviemanagement2.R;

public class EditEpisodeActivity extends AppCompatActivity {
    EditText name, duration;
    ImageView imageView;
    RatingView rate;
    private int dramaPosition, epPosition;
    Drama drama;
    Episodes ep;
    private float ratingInStar;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_episode);
        dramaPosition = getIntent().getIntExtra("keyDramaEpisodePosition", -1);
        epPosition = getIntent().getIntExtra("episodePosition", -1);


        drama = (Drama) DataProvider.movieObjects.get(dramaPosition);
        ep = drama.getEpisodes().get(epPosition);
        imageView = findViewById(R.id.imageView3);

        getSupportActionBar().setTitle(ep.getEpisodeName());
        if (drama.getTitle().equals("Twilight")){
            imageView.setImageResource(R.drawable.twilight);
        }else if (drama.getTitle().equals("The mummy")){
            imageView.setImageResource(R.drawable.mummy);
        }else {
            imageView.setImageResource(R.color.colorPrimaryDark);
        }

        name = findViewById(R.id.editEpName);
        duration = findViewById(R.id.editEpDuration);
        rate = findViewById(R.id.rv);
        ratingBar = findViewById(R.id.ratingBar);


        name.setText(ep.getEpisodeName());
        duration.setText(ep.getEpisodeDuration());
        rate.setRating(ep.getEpisodeRattingDouble());
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingInStar = rating;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item2:
                ep.setEpisodeName(name.getText().toString());
                ep.setEpisodeDuration(duration.getText().toString());

                ep.setEpisodeRattingDouble(ratingInStar);
                Log.d("TienS", "onEditEp: " + ratingInStar);
                Intent intent = new Intent(this, LibraryMovieActivity.class);
                setResult(Activity.RESULT_OK, intent);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    public void onDeleteEpClick(View view) {
        drama.getEpisodes().remove(epPosition);
        Intent intent = new Intent(this, LibraryMovieActivity.class);
        startActivityForResult(intent, 21);
    }
}
