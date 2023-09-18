package com.example.newclass;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TaplayoutActivity2 extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taplayout2);

         Toolbar toolbar = findViewById(R.id.toolber_taplay);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        mViewPager = findViewById(R.id.viewpager);

        initViewPager();

    }

    private void initViewPager() {



        mTabLayout = findViewById(R.id.tabs);

        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("体育");
        titles.add("巴萨");
        titles.add("购物");
        titles.add("明星");
        titles.add("视频");
        titles.add("健康");
        titles.add("励志");
        titles.add("图文");
        titles.add("本地");
        titles.add("动漫");
        titles.add("搞笑");
        titles.add("精选");

        for (int i=0;i<titles.size();i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i=0;i<titles.size();i++){
            fragments.add(new ListFragment());
        }
        FragmentAdapter mFragmentAdapter =
                new FragmentAdapter(getSupportFragmentManager(),fragments,titles);

        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);

    }

    public void checkin(View view) {


        Snackbar.make(view,"点击成功",Snackbar.LENGTH_SHORT).show();
    }
}