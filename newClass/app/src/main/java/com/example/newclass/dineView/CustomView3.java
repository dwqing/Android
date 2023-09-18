package com.example.newclass.dineView;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomView3 extends View {
    private int lastX;
    private int lastY;
    public CustomView3(Context context) {
        super(context);
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

                LinearLayout.LayoutParams layoutParams =
                        (LinearLayout.LayoutParams)
                        getLayoutParams();

                layoutParams.leftMargin = getLeft()+offsetX;
                layoutParams.topMargin = getTop()+offsetY;
                setLayoutParams(layoutParams);
                break;
        }
        return true;
    }
}
