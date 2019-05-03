package com.example.moviemanagement2.MainMenu;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.moviemanagement2.AddActivity.AddDramaActivity;
import com.example.moviemanagement2.AddActivity.AddFilmActivity;
import com.example.moviemanagement2.Adapter.MovieAdapter;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Film;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.MovieObject;
import com.example.moviemanagement2.R;
import com.example.moviemanagement2.ShowActivity.ShowDramaActivity;
import com.example.moviemanagement2.ShowActivity.ShowFilmActivity;

import java.util.List;


public class LibraryMovieActivity extends AppCompatActivity {


    ListView listView;
    private MovieAdapter adapter;
    private String messageTitleBar = "";
    List<MovieObject>movieObjectList = DataProvider.movieObjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_movie);
        getSupportActionBar().setTitle(R.string.my_library);

            listView = findViewById(R.id.gerne_series);

            adapter = new MovieAdapter(this, movieObjectList);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Object movie = parent.getItemAtPosition(position);

                    if (movie instanceof Film) {
                        Intent intent = new Intent(view.getContext(), ShowFilmActivity.class);

                        intent.putExtra("keyTitle", messageTitleBar);

                        intent.putExtra("keyPositionFilm", position);
                        startActivityForResult(intent, 4);
                    }else if (movie instanceof Drama){
                        Intent intent1 = new Intent(view.getContext(), ShowDramaActivity.class);
                        intent1.putExtra("keyPositionDrama", position);
                        startActivityForResult(intent1, 2);
                    }


//                    intent.putExtra("keyImage", image );

                }
            });
    }

    public void onAddButtonClick(View view) {
        Intent intent = new Intent(this, AddFilmActivity.class);
        startActivity(intent);
    }

    public void onAddDramaButtonClick(View view) {
        Intent intent = new Intent(this, AddDramaActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
