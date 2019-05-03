package com.example.moviemanagement2.AddActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Drama;
import com.example.moviemanagement2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddDramaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText title, releaseYear, director, staring, description, country;
    private Spinner genre;
    private String newGenre;
    private String filePath = null;
    ImageView dramaImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drama);
        getSupportActionBar().setTitle("New Drama");
        title = findViewById(R.id.addDramaTitle);
        genre = findViewById(R.id.addDramaGenre);
        releaseYear = findViewById(R.id.addDramaReleaseYear);
        director = findViewById(R.id.addDramaDirector);
        staring = findViewById(R.id.addDramaStaring);
        description = findViewById(R.id.addDramaDescription);
        country = findViewById(R.id.addDramaCountry);
        dramaImage = findViewById(R.id.addImageDrama);
        dramaImage.setImageResource(R.drawable.noun);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.gernes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter2);
        genre.setOnItemSelectedListener(this);

    }



    public void onAddNewDramaClick(View view) {
        String newTitle = title.getText().toString();
        String newYear = releaseYear.getText().toString();
        String newDirector =  director.getText().toString();
        String newStaring = staring.getText().toString();
        String newDescription = description.getText().toString();
        String newCountry = country.getText().toString();
        DataProvider.movieObjects.add(new Drama(newTitle, newGenre,newYear, newDirector, newStaring, false,filePath,newDescription, newCountry));
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        newGenre = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String saveToInternalStorage(Bitmap bitmapImage){
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

    public void onAddDramaImageClick(View view) {
        Intent photoLibrary = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(photoLibrary, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            Bitmap photo = null;
            try {
                photo = (Bitmap) data.getExtras().get("data");
            } catch (Exception e) {
                e.printStackTrace();
            }
            filePath = saveToInternalStorage(photo);
            File imgFile = new File(filePath);

            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                dramaImage.setImageBitmap(myBitmap);
            }

        }
    }
}
