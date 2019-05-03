package com.example.moviemanagement2.AddActivity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moviemanagement2.CustomDrawing.CustomFav;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.DataProvider;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Film;
import com.example.moviemanagement2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddFilmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    private EditText title, date, director, star, duration;
    private CustomFav favourite;
    private String genre;
    private ImageView imageView;
    CheckBox nominate;
    boolean isNominate;
    boolean isFav;
    private String filePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        getSupportActionBar().setTitle("New Film");
        spinner = findViewById(R.id.addGenre);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.gernes, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(this);

        title = findViewById(R.id.movieNameView);
        date = findViewById(R.id.releaseDateView);
        director = findViewById(R.id.directorNameView);
        star = findViewById(R.id.staringNameView);
        duration = findViewById(R.id.durationView);
        favourite = findViewById(R.id.addToFav);
        imageView = findViewById(R.id.addImage);
        imageView.setImageResource(R.drawable.noun);
        nominate = findViewById(R.id.nominationView);

        nominate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("TienS", isChecked+"");
                isNominate = isChecked;
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favourite.swap();
                if (favourite.getIsFav()==true){
                    isFav = true;
                }else if (favourite.getIsFav() == false){
                    isFav = false;
                }
            }
        });


    }

    public void onAddNewMovieClick(View view) {
        String userEnterTitle = title.getText().toString();
        final String userEnterDate = date.getText().toString();
        String userEnterDirector = director.getText().toString();
        String userEnterStar = star.getText().toString();
        String userEnterDuration = duration.getText().toString();

        DataProvider.movieObjects.add(new Film(userEnterTitle, genre,userEnterDate, userEnterDirector, userEnterStar,isFav, filePath,userEnterDuration,isNominate));
        finish();
    }

    public void onCancelClick(View view) {
        Toast.makeText(this, "Cancel add new movie", Toast.LENGTH_LONG);
        finish();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        genre = parent.getItemAtPosition(position).toString();
        if (genre.equals("Genres")){
            genre = null;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onAddImageClick(View view) {
        Intent photoLibrary = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(photoLibrary, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
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
                imageView.setImageBitmap(myBitmap);
            }

        }else {
            Log.i("tienS", "onActivityResult: ");
        }
    }
}
