package com.example.moviemanagement2.Adapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Film;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.MovieObject;
import com.example.moviemanagement2.R;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieObject> {
    List<MovieObject> movies;
    LayoutInflater inflater;

    public MovieAdapter(Context context, List<MovieObject> objects) {
        super(context, R.layout.mylist, objects);
        movies = objects;
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mylist, parent, false);
        }
        TextView title = convertView.findViewById(R.id.titleView);
        TextView genre = convertView.findViewById(R.id.genreView);
        ImageView image = convertView.findViewById(R.id.movieImage);
        TextView type = convertView.findViewById(R.id.typeView);

        MovieObject movie = movies.get(position);
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        if (movie instanceof Film) {
            type.setText("Type: FILM");
        }else if (movie instanceof Drama){
            type.setText("Type: DRAMA");
        }

        if (movie.getPhotoFileName()!=null) {
            Bitmap bitmap = movie.getImageBitmap(getContext());
            if (bitmap != null) {
                image.setImageBitmap(bitmap);
            }
        }

        return convertView;
    }
}
