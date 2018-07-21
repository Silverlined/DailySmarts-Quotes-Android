package com.example.dimitriygeorgiev.dailysmarts.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.dimitriygeorgiev.dailysmarts.R;
import com.example.dimitriygeorgiev.dailysmarts.models.room.ViewModel;
import com.example.dimitriygeorgiev.dailysmarts.ui.adapters.PagerAdapter;
import com.example.dimitriygeorgiev.dailysmarts.ui.fragments.DailyQuotesFragment;
import com.example.dimitriygeorgiev.dailysmarts.ui.fragments.MyQuotesFragment;
import com.example.dimitriygeorgiev.dailysmarts.ui.fragments.SharedViewModel;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    private SharedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        setUpToolbar();
        viewModel = new SharedViewModel();

        setUpFragmentsUi();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setUpFragmentsUi() {
        tabLayout.addTab(tabLayout.newTab().setText("DAILY QUOTE"));
        tabLayout.addTab(tabLayout.newTab().setText("MY QUOTES"));

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu, menu);
        return true;
    }
}
