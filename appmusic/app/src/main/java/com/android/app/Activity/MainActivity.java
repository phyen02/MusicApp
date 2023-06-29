package com.android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.android.app.Adapter.MainViewPagerAdapter;
import com.android.app.Fragment.Fragment_Account;
import com.android.app.Fragment.Fragment_Home;
import com.android.app.Fragment.Fragment_Library;
import com.android.app.Fragment.Fragment_Search;
import com.android.app.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        init();
    }

    private void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Home(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Search(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Library(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Account(),"");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.iconhome);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconlibrary);
        tabLayout.getTabAt(3).setIcon(R.drawable.iconuser);
    }

    private void anhxa(){
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}