package com.example.criminalintent2;



import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;


public class MainActivity extends  SingleFragmentActivity{

    private static final String EXTRA_CRIME_ID = "com.example.criminal.crime.id";

    //调用这个方法存储crimeID
    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext,MainActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }

    //调用Fragment并且传递ID
    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

}