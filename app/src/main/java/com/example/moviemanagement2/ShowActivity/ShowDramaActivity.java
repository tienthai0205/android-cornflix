package com.example.moviemanagement2.ShowActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviemanagement2.CustomDrawing.CustomFav;
import com.example.moviemanagement2.EditActivity.EditDramaActivity;
import com.example.moviemanagement2.MainMenu.LibraryEpisodeActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.R;

public class ShowDramaActivity extends AppCompatActivity {
    Drama drama;
    TextView titleDramaView, genreDramaView, releaseDramaYearView, directorDramaView, starringDramaView,
            descriptionDramaView, productionDramaView;
    ImageView imageView;
    private int position;
    private CustomFav customFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_drama);
        position = getIntent().getIntExtra("keyPositionDrama", -1);
        drama = ((Drama) DataProvider.movieObjects.get(position));
        getSupportActionBar().setTitle(drama.getTitle());
        titleDramaView = findViewById(R.id.titleDramaShow);
        genreDramaView = findViewById(R.id.genreDramaShow);
        releaseDramaYearView = findViewById(R.id.releaseYearDramaShow);
        directorDramaView = findViewById(R.id.directorDramaShow);
        starringDramaView = findViewById(R.id.starringDramaShow);
        descriptionDramaView = findViewById(R.id.descriptionDramaShow);
        productionDramaView = findViewById(R.id.countryToShow);
        imageView = findViewById(R.id.imageDramaShow);
        customFav = findViewById(R.id.favouriteDramaView);
        customFav.setFav(drama.isFavorite());

        if (drama.getPhotoFileName() != null) {
            Bitmap bitmap = drama.getImageBitmap(this);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }


        titleDramaView.setText(drama.getTitle());
        genreDramaView.setText(drama.getGenre());
        releaseDramaYearView.setText(drama.getReleaseYear());
        directorDramaView.setText(drama.getDirector());
        starringDramaView.setText(drama.getStarring());
        descriptionDramaView.setText(drama.getDescription());
        productionDramaView.setText(drama.getProductionCountry());

    }

    public void onShowEpisodeClick(View view) {
        Intent intent = new Intent(this, LibraryEpisodeActivity.class);
        intent.putExtra("keyPosition", position);
        startActivityForResult(intent, 3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, EditDramaActivity.class);
                intent.putExtra("positionDrama", position);
                startActivityForResult(intent, 1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
