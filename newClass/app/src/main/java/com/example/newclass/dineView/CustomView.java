package com.example.newclass.dineView;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private int lastX;
    private int lastY;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case ACTION_DOWN:
                lastX =x;
                lastY =y;
                break;
            case ACTION_MOVE:
                int offsetX = x -lastY;
                int offsetY = y -lastY;
                layout(getLeft()+offsetX
                ,getTop()+offsetY
                ,getRight()+offsetX
                ,getBottom()+offsetY);
                break;
        }
        return true;
    }
}
