package com.example.moviemanagement2.EditActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.moviemanagement2.CustomDrawing.CustomFav;
import com.example.moviemanagement2.MainMenu.LibraryMovieActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Favorite;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Film;
import com.example.moviemanagement2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EditFilmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText title,  releaseYear,director, star, duration;
    CheckBox nomination;
    Spinner genre;
    private String filePath = null;
    Film filmSelected;
    CustomFav customFav;
    private int keyPosition;
    private boolean isFav;
    private int spinnerPosition;
    ImageView imageFilmView;
    String newGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_film);
        keyPosition = getIntent().getIntExtra("keyPosition", -1);
        filmSelected = (Film) DataProvider.movieObjects.get(keyPosition);
        getSupportActionBar().setTitle("");
        customFav = findViewById(R.id.customFavEdit);
        imageFilmView = findViewById(R.id.filmImageEdit);
        if (filmSelected.getPhotoFileName()!= null) {
            Bitmap bitmap = filmSelected.getImageBitmap(this);
            if (bitmap != null){
                imageFilmView.setImageBitmap(bitmap);
            }
        }
        findById();
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.gernes, android.R.layout.simple_spinner_item);
        ArrayList<CharSequence> newList = new ArrayList<CharSequence>();
        newList.add(adapter2.getItem(0));
        newList.add(adapter2.getItem(1));
        newList.add(adapter2.getItem(2));
        newList.add(adapter2.getItem(3));
        newList.add(adapter2.getItem(4));
        newList.add(adapter2.getItem(5));

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i = 0; i <newList.size() ; i++) {
            if (newList.get(i).toString().equals(filmSelected.getGenre())){
                spinnerPosition = i;
            }
        }
        genre.setAdapter(adapter2);
        genre.setSelection(spinnerPosition);
        genre.setOnItemSelectedListener(this);
        setter();
        customFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customFav.swap();
                if (customFav.getIsFav()==true){
                    isFav = true;
                }else if (customFav.getIsFav() == false){
                    isFav = false;
                }
                filmSelected.setFavorite(isFav);

                Log.d("TienS", filmSelected.getTitle() + filmSelected.isFavorite());
            }
        });



    }

    private void findById(){
        title = findViewById(R.id.titleTextToEdit);
        genre = findViewById(R.id.genreTextToEdit);
        releaseYear = findViewById(R.id.releaseYearTextToEdit);
        director = findViewById(R.id.directorTextToEdit);
        star = findViewById(R.id.starringTextToEdit);
        duration = findViewById(R.id.descriptionTextToEdit);
        nomination = findViewById(R.id.nominationCheckBoxEdit);
    }

    public void setter(){
        title.setText(filmSelected.getTitle());

        releaseYear.setText(filmSelected.getReleaseYear());
        director.setText(filmSelected.getDirector());
        star.setText(filmSelected.getStarring());
        duration.setText(filmSelected.getDuration());
        nomination.setChecked(filmSelected.isNominated());
        customFav.setFav(filmSelected.isFavorite());

        nomination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filmSelected.setNominated(isChecked);
                Log.i("TienS", ""+ filmSelected.isNominated()+"");
            }
        });

    }
    private void updateFav(){
        if (isFav == true){
            Favorite.movieObjectsInFavourite.add(filmSelected);
        }else if (isFav == false){
            if (Favorite.movieObjectsInFavourite.contains(filmSelected)){
                int pos = 0;
                for (int i = 0; i < Favorite.movieObjectsInFavourite.size() ; i++) {
                    if (filmSelected.getTitle().equals(Favorite.movieObjectsInFavourite.get(i))){
                        pos =i;
                    }
                }
                Favorite.movieObjectsInFavourite.remove(pos);
            }
        }
    }

    public void getEdit(){
        updateFav();
        String updateTitle = title.getText().toString();
        String updateGenre = newGenre;
        String updateYear = releaseYear.getText().toString();
        String updateDirector = director.getText().toString();
        String updateStars = star.getText().toString();
        String updateDuration = duration.getText().toString();

        filmSelected.setTitle(updateTitle);
        filmSelected.setGenre(updateGenre);
        filmSelected.setReleaseYear(updateYear);
        filmSelected.setDirector(updateDirector);
        filmSelected.setStarring(updateStars);
        filmSelected.setDuration(updateDuration);
        if (filePath!=null) {
            filmSelected.setPhotoFileName(filePath);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:
                getEdit();
                Intent intent = new Intent(this, LibraryMovieActivity.class);
                setResult(Activity.RESULT_OK, intent);
                Log.i("TienSS", filmSelected.getTitle());
                Log.i("TienSS", filmSelected.isNominated()+"");
                startActivity(intent);
            default: return super.onOptionsItemSelected(item);

        }
    }

    public void onDeleteFilmClick(View view) {
        DataProvider.movieObjects.remove(keyPosition);
        for (int i = 0; i <DataProvider.movieObjects.size() ; i++) {
            Log.i("TienS", DataProvider.movieObjects.get(i).getTitle());
        }
        Intent intent = new Intent(this, LibraryMovieActivity.class);
        startActivity(intent);
    }

    public void editFilmImage(View view) {
        Intent photoLibrary = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(photoLibrary, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            filePath = saveToInternalStorage(photo);
            File imgFile = new File(filePath);

            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageFilmView.setImageBitmap(myBitmap);
            }

        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("BookPictures", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"image.jpg");
        // for book use the name of the book
        //book.getName +"id"+ ".jpg"

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            //this one is only to change the size
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        newGenre = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
