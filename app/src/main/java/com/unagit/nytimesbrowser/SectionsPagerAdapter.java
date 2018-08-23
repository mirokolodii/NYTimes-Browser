package com.unagit.nytimesbrowser;

import android.os.Bundle;
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

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.Queries.queryLabel, position);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        Log.d(this.getClass().getSimpleName(), "queryType: " + String.valueOf(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return Constants.Tabs.TABS_COUNT;
    }
}
