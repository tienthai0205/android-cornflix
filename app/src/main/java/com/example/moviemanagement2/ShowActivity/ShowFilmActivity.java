package com.example.moviemanagement2.ShowActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviemanagement2.CustomDrawing.CustomFav;
import com.example.moviemanagement2.EditActivity.EditFilmActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Film;
import com.example.moviemanagement2.R;

public class ShowFilmActivity extends AppCompatActivity {
    TextView titleView, genreView, releaseYearView, directorView, starringView, descriptionView;
    ImageView imageView;
    CheckBox nominated;
    private Film film;
    private  int position;
    private CustomFav customFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_film);

        position = getIntent().getIntExtra("keyPositionFilm", -1);
        film = (Film) DataProvider.movieObjects.get(position);
        customFav = findViewById(R.id.customFav4);


        getSupportActionBar().setTitle(film.getTitle());
        findById();

        if (film.getPhotoFileName()!= null) {
            Bitmap bitmap = film.getImageBitmap(this);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
        setText();
    }

    private void findById() {
        titleView = findViewById(R.id.titleText);
        genreView = findViewById(R.id.genreText);
        releaseYearView = findViewById(R.id.releaseYearText);
        directorView = findViewById(R.id.directorText);
        starringView = findViewById(R.id.starringText);
        descriptionView = findViewById(R.id.descriptionText);
        nominated = findViewById(R.id.nominationCheckBox);
        imageView = findViewById(R.id.filmImageShow);

    }

    private void setText() {

        titleView.setText(film.getTitle());
        genreView.setText(film.getGenre());
        releaseYearView.setText(film.getReleaseYear());
        directorView.setText(film.getDirector());
        starringView.setText(film.getStarring());
        descriptionView.setText(film.getDuration());
        nominated.setChecked(film.isNominated());
        customFav.setFav(film.isFavorite());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(this, EditFilmActivity.class);
                intent.putExtra("keyPosition", position);
                startActivityForResult(intent, 0);
                default: return super.onOptionsItemSelected(item);
        }
    }


}
