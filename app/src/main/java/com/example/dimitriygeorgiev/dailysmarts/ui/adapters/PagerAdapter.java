package com.example.dimitriygeorgiev.dailysmarts.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dimitriygeorgiev.dailysmarts.ui.fragments.DailyQuotesFragment;
import com.example.dimitriygeorgiev.dailysmarts.ui.fragments.MyQuotesFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DailyQuotesFragment dailyQuotes = new DailyQuotesFragment();
                return dailyQuotes;
            case 1:
                MyQuotesFragment myQuotes = new MyQuotesFragment();
                return myQuotes;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
