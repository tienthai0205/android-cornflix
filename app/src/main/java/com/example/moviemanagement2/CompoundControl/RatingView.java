package com.example.moviemanagement2.CompoundControl;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.moviemanagement2.R;

public class RatingView extends LinearLayout {

    private TextView ratingInText;
    private RatingBar ratingBar;
    public RatingView(Context context) {
        super(context);
        init();
    }

    public RatingView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RatingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ratingview, this);
        ratingInText = findViewById(R.id.ratingViewInText);
        ratingBar = findViewById(R.id.ratingBar);
    }

    public void setRating(double rating){
        this.ratingInText.setText("The rating is "+ rating);
        this.ratingBar.setRating((float) rating);
    }


}
