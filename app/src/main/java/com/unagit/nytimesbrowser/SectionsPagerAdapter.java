package com.unagit.nytimesbrowser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.unagit.nytimesbrowser.helpers.Constants;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.Tabs.MOST_EMAILED_TAB:
                return new Fragment();

            case Constants.Tabs.MOST_SHARED_TAB:
                return new Fragment();

            case Constants.Tabs.MOST_VIEWED_TAB:
                return new Fragment();

            default:
                Log.e(this.getClass().getName(), "PagerAdapter returned empty fragment, which means unhandled case.");
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return Constants.Tabs.TABS_COUNT;
    }
}
