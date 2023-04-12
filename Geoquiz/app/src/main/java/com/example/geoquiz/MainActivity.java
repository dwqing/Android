package com.example.geoquiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mTureButton;
    private Button mFalseButton;

    public MainActivity(Button mTureButton, Button mFalseButton) {
        this.mTureButton = mTureButton;
        this.mFalseButton = mFalseButton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(view -> Toast.makeText(MainActivity.this,
                R.string.incorrect_toast,
                Toast.LENGTH_LONG).show());
        mTureButton = findViewById(R.id.ture_button);
        mTureButton.setOnClickListener(view -> Toast.makeText(MainActivity.this,
                R.string.correct_toast,
                Toast.LENGTH_LONG).show());
    }
}