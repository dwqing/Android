package com.example.newclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class CollapsingToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle("哆啦a盟");
        RecyclerView m = findViewById(R.id.recycleView);
        m.setLayoutManager(new StaggeredGridLayoutManager(
                1,StaggeredGridLayoutManager.VERTICAL
        ));
        m.setItemAnimator(new DefaultItemAnimator());

        m.setAdapter(new RecyclerViewAdapter(CollapsingToolbarActivity.this));

    }
}