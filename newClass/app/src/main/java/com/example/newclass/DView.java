package com.example.newclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AnimationUtils;

import com.example.newclass.dineView.CustomView3;

public class DView extends AppCompatActivity {

    private CustomView3 mCustomView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dview);

        mCustomView3  = findViewById(R.id.mycustcom);
        mCustomView3.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));
    }
}