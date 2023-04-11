package com.example.criminalintent2;

import androidx.fragment.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {
    //第一个activity，通过activity来控制fragment
    @Override
    protected Fragment createFragment() {
        //返回一个实例
        return new CrimeListFragment();
    }

}
