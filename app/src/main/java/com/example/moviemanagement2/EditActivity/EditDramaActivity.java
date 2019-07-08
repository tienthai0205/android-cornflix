package com.example.moviemanagement2.EditActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.moviemanagement2.CustomDrawing.CustomFav;
import com.example.moviemanagement2.MainMenu.LibraryMovieActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Favorite;
import com.example.moviemanagement2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EditDramaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText titleDramaView, releaseDramaYearView, directorDramaView, starringDramaView,
            descriptionDramaView,productionDramaView;
    Drama dramaSelected;
    Spinner genreDramaView;
    private int position;
    ImageView imageView;
    private String filePath = null;
    private boolean isFav;
    CustomFav customFav;
    private String newGenre;
    private int spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drama);
        position = getIntent().getIntExtra("positionDrama", 1);
        dramaSelected = (Drama) DataProvider.movieObjects.get(position);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setTitle(dramaSelected.getTitle());

        findById();

        if (dramaSelected.getPhotoFileName()!= null) {
            Bitmap bitmap = dramaSelected.getImageBitmap(this);
            if (bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
        }
        customFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customFav.swap();
                if (customFav.getIsFav()==true){
                    isFav = true;
                    Favorite.movieObjectsInFavourite.add(dramaSelected);
                }else if (customFav.getIsFav() == false){
                    isFav = false;
                    if (Favorite.movieObjectsInFavourite.contains(dramaSelected)){
                        int pos = 0;
                        for (int i = 0; i < Favorite.movieObjectsInFavourite.size() ; i++) {
                            if (dramaSelected.getTitle().equals(Favorite.movieObjectsInFavourite.get(i))){
                                pos =i;
                            }
                        }
                        Favorite.movieObjectsInFavourite.remove(pos);
                    }
                }
                dramaSelected.setFavorite(isFav);

                Log.d("TienS", dramaSelected.getTitle() + dramaSelected.isFavorite());
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.gernes, android.R.layout.simple_spinner_item);
        ArrayList<CharSequence>newList = new ArrayList<CharSequence>();
        newList.add(adapter2.getItem(0));
        newList.add(adapter2.getItem(1));
        newList.add(adapter2.getItem(2));
        newList.add(adapter2.getItem(3));
        newList.add(adapter2.getItem(4));
        newList.add(adapter2.getItem(5));

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i = 0; i <newList.size() ; i++) {
            if (newList.get(i).toString().equals(dramaSelected.getGenre())){
                spinnerPosition = i;
            }
        }
        genreDramaView.setAdapter(adapter2);
        genreDramaView.setSelection(spinnerPosition);
        genreDramaView.setOnItemSelectedListener(this);


            setter();

    }


    public void getUpdateDrama(){
        dramaSelected.setTitle(titleDramaView.getText().toString());
        dramaSelected.setReleaseYear(releaseDramaYearView.getText().toString());
        dramaSelected.setDirector(directorDramaView.getText().toString());
        dramaSelected.setStarring(starringDramaView.getText().toString());
        dramaSelected.setDescription(descriptionDramaView.getText().toString());
        dramaSelected.setProductionCountry(productionDramaView.getText().toString());
        dramaSelected.setGenre(newGenre);
        if (filePath!=null) {
            dramaSelected.setPhotoFileName(filePath);
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
                getUpdateDrama();
                Intent intent = new Intent(this, LibraryMovieActivity.class);
                setResult(Activity.RESULT_OK, intent);
                startActivity(intent);
                default: return super.onOptionsItemSelected(item);
        }
    }


    public void onDeleteDramaClick(View view) {
        DataProvider.movieObjects.remove(position);
        Intent intent = new Intent(this, LibraryMovieActivity.class);
        startActivity(intent);
    }

    public void editDramaImage(View view) {
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
                imageView.setImageBitmap(myBitmap);
            }

        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("MoviePictures", Context.MODE_PRIVATE);
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

    private void setter(){
        titleDramaView.setText(dramaSelected.getTitle());
        releaseDramaYearView.setText(dramaSelected.getReleaseYear());
        directorDramaView.setText(dramaSelected.getDirector());
        starringDramaView.setText(dramaSelected.getStarring());
        descriptionDramaView.setText(dramaSelected.getDescription());
        productionDramaView.setText(dramaSelected.getProductionCountry());
        customFav.setFav(dramaSelected.isFavorite());

    }

    private void findById(){
        titleDramaView = findViewById(R.id.titleDramaText);
        genreDramaView = findViewById(R.id.genreDramaEdit);
        releaseDramaYearView = findViewById(R.id.releaseYearDramaText);
        directorDramaView = findViewById(R.id.directorDramaText);
        starringDramaView = findViewById(R.id.starringDramaText);
        descriptionDramaView = findViewById(R.id.descriptionDramaText);
        productionDramaView = findViewById(R.id.countryToEdit);
        imageView = findViewById(R.id.imageDrama);
        customFav = findViewById(R.id.customFavDramaEdit);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        newGenre = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
