package com.example.moviemanagement2.ModelClasses.ModelClassDrama;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MovieObject  {

    private String title;
    private String genre;
    private String releaseYear;
    private String director;
    private String starring;
    private String photoFileName = null;
    private boolean isFavorite;


    public MovieObject(String title, String genre, String releaseYear, String director, String starring) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.starring = starring;

    }

    public MovieObject(String title, String genre, String releaseYear, String director, String starring, boolean isFavorite) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.starring = starring;
        this.isFavorite = isFavorite;
    }

    public MovieObject(String title, String genre, String releaseYear, String director, String starring, String photoFileName, boolean isFavorite) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.starring = starring;
        this.photoFileName = photoFileName;
        this.isFavorite = isFavorite;
    }

    public MovieObject(String title, String genre, String releaseYear, String director, String starring,boolean isFavorite, String photoFileName) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.starring = starring;
        this.isFavorite = isFavorite;
        this.photoFileName = photoFileName;
    }

    public MovieObject(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public MovieObject() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public boolean isFavorite() {
        return isFavorite;
    }


    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    public Bitmap getImageBitmap(Context context) {
        File imgFile = new File(photoFileName);
        if(imgFile.exists()){
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        else {
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open(photoFileName);
                Drawable d = Drawable.createFromStream(inputStream, null);
                return ((BitmapDrawable)d).getBitmap();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null){
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
