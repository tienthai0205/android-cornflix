package com.example.moviemanagement2.MainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.moviemanagement2.Adapter.EpisodeAdapter;
import com.example.moviemanagement2.AddActivity.AddEpActivity;
import com.example.moviemanagement2.EditActivity.EditEpisodeActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Episodes;
import com.example.moviemanagement2.R;
import com.example.moviemanagement2.ShowActivity.ShowDramaActivity;

import java.util.List;

public class LibraryEpisodeActivity extends AppCompatActivity {

    ListView epListView;
    private EpisodeAdapter epAdapter;
    List<Episodes> episodeList;
    Drama movieObjectDrama;
    private int dramaPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_episode);
        dramaPosition = getIntent().getIntExtra("keyPosition", -1);

        movieObjectDrama = ((Drama) DataProvider.movieObjects.get(dramaPosition));

        Intent intent1= new Intent(this, ShowDramaActivity.class);
        intent1.putExtra("keyPositionDrama", dramaPosition);

        Log.i("TienS", dramaPosition+"");

        getSupportActionBar().setTitle("Episodes in "+ movieObjectDrama.getTitle());


        Log.d("aa", "onCreate: " + dramaPosition);

        episodeList = movieObjectDrama.getEpisodes();
        epListView = findViewById(R.id.epList);
        epAdapter = new EpisodeAdapter(this,episodeList);
        epListView.setAdapter(epAdapter);
        epListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), EditEpisodeActivity.class);

                    intent.putExtra("episodePosition", position);
                    intent.putExtra("keyDramaEpisodePosition", dramaPosition);

                startActivityForResult(intent, 5);
            }
        });
    }

    public void onAddEpClickTWICE(View view) {
        Intent intent = new Intent(this, AddEpActivity.class);
        intent.putExtra("keyDramaNamePosition", dramaPosition);
        startActivityForResult(intent, 3);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i("TIENNN", "onResume: ");
//        epAdapter.notifyDataSetChanged();
//    }
}
