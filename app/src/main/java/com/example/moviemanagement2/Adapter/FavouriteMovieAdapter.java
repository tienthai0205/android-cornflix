package com.example.moviemanagement2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.moviemanagement2.MainMenu.FavoriteActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Favorite;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Film;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.MovieObject;
import com.example.moviemanagement2.R;
import com.example.moviemanagement2.ShowActivity.ShowDramaActivity;
import com.example.moviemanagement2.ShowActivity.ShowFilmActivity;

import java.util.List;

public class FavouriteMovieAdapter extends ArrayAdapter  {

    List<MovieObject> favoriteList;
    private LayoutInflater inflater;
    ImageView favouriteImage;
    FavoriteActivity fav;
    private int positionMovie;
    private View innerView;
    Button button;


    public FavouriteMovieAdapter(Context mContext, List objects) {
        super(mContext, R.layout.favourite_layout, objects);
        favoriteList = objects;
        inflater = LayoutInflater.from(mContext);
    }


    public View getView(int position, View convertView, final ViewGroup parent){
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.favourite_layout, parent, false);
        }

        TextView favouriteMovieName, favouriteGenre, favouriteYear;

        final int pos = position;
        favouriteMovieName = convertView.findViewById(R.id.favouriteMovieName);
        favouriteGenre = convertView.findViewById(R.id.favouriteMovieGenre);
        favouriteYear = convertView.findViewById(R.id.favouriteMovieReleaseYear);
        favouriteImage = convertView.findViewById(R.id.favouriteImage);
        button = convertView.findViewById(R.id.dotsFav);
        innerView = convertView;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(parent.getContext(), v);
                popup.inflate(R.menu.menu_pop);
                popup.show();
                Log.i("tienS", pos + "adapter");

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        MovieObject favourite = Favorite.movieObjectsInFavourite.get(pos);
                        switch (item.getItemId()){
                            case R.id.action_remove:
                                Log.i("tienS", pos +"");
                                for (int i = 0; i < DataProvider.movieObjects.size(); i++) {
                                    if (favourite.getTitle().equals(DataProvider.movieObjects.get(i).getTitle())){
                                        Favorite.movieObjectsInFavourite.remove(pos);
                                        DataProvider.movieObjects.get(i).setFavorite(false);
                                    }
                                }
                                notifyDataSetChanged();
                                return true;
                            case R.id.action_show_movie:
                                for (int i = 0; i < DataProvider.movieObjects.size(); i++) {
                                    if (favourite.getTitle().equals(DataProvider.movieObjects.get(i).getTitle())){
                                        positionMovie = i;
                                        if (favourite instanceof Film){
                                            Intent intent = new Intent(parent.getContext(), ShowFilmActivity.class);
                                            intent.putExtra("keyPositionFilm", positionMovie);
                                            Log.i("tienS", DataProvider.movieObjects.get(positionMovie).getTitle());
                                            parent.getContext().startActivity(intent);
                                        }else if (favourite instanceof Drama){
                                            Intent intent2 = new Intent(parent.getContext(), ShowDramaActivity.class);
                                            intent2.putExtra("keyPositionDrama", positionMovie);
                                            parent.getContext().startActivity(intent2);
                                            Log.i("tienS", DataProvider.movieObjects.get(positionMovie).getTitle());

                                        }
                                    }
                                }
                            default:
                                return false;
                        }
                    }
                });
            }
        });


        final MovieObject favoriteMovie = favoriteList.get(position);
        favouriteMovieName.setText(favoriteMovie.getTitle());
        favouriteGenre.setText(favoriteMovie.getGenre());
        favouriteYear.setText(favoriteMovie.getReleaseYear());

        MovieObject movieObject = favoriteList.get(position);

        if (movieObject.getPhotoFileName()!=null) {
            Bitmap bitmap = movieObject.getImageBitmap(getContext());
            if (bitmap != null) {
                favouriteImage.setImageBitmap(bitmap);
            }
        }


        return convertView;
    }

}
