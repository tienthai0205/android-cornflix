package com.example.moviemanagement2.CustomDrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import android.view.View;

import com.example.moviemanagement2.R;

public class CustomFav extends View {
    private Path path;
    private Paint paint;


    public CustomFav(Context context) {
        super(context);
        init(null);
    }

    public CustomFav(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomFav(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomFav(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init(@Nullable AttributeSet set){
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);

    }
    public void swap(){
        paint.setStyle(paint.getStyle() == Style.FILL ? Style.STROKE:Style.FILL);
        invalidate();
    }


    public boolean getIsFav(){
        if (paint.getStyle() == Style.FILL){
            return true;
        }else {
            return false;
        }
    }


    public void setFav(boolean isFavourite){
        if (isFavourite){
            paint.setStyle(Style.FILL);
        }else {
            paint.setStyle(Style.STROKE);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Fill the canvas with background color
        canvas.drawColor(Color.TRANSPARENT);


        float width = getContext().getResources().getDimension(R.dimen.heart_width);
        float height = getContext().getResources().getDimension(R.dimen.heart_height);

        // Starting point
        path.moveTo(width / 2, height / 5);

        // Upper left path
        path.cubicTo(5 * width / 14, 0,
                0, height / 15,
                width / 28, 2 * height / 5);

        // Lower left path
        path.cubicTo(width / 14, 2 * height / 3,
                3 * width / 7, 5 * height / 6,
                width / 2, height);

        // Lower right path
        path.cubicTo(4 * width / 7, 5 * height / 6,
                13 * width / 14, 2 * height / 3,
                27 * width / 28, 2 * height / 5);

        // Upper right path
        path.cubicTo(width, height / 15,
                9 * width / 14, 0,
                width / 2, height / 5);


        canvas.drawPath(path,paint);

    }

}
