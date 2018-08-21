package com.unagit.nytimesbrowser;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.unagit.nytimesbrowser.helpers.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL = "https://api.nytimes.com/svc/mostpopular/v2/mostemailed/all-sections/30.json?api-key=f9e16b9b85ae461e82094903d9676078";

        // Set tabs and show them on screen, using ViewPager.
        setupViewPagerAndTabLayout();
    }

    /**
     * Setup ViewPager with adapter.
     * Setup TabLayout to work with ViewPager.
     */
    private void setupViewPagerAndTabLayout() {
        // PagerAdapter for ViewPager
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with PagerAdapter.
        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Custom animated transformation between tabs
//        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //Setup a TabLayout to work with ViewPager (get tabs from it).
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        // Each tab: remove title text and set icons
        setTabTitles(tabLayout);

        /*
         * Listener for TabLayout tabs selection changes
         */
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * Setup titles for tabs in tabLayout.
     * @param tabLayout use tabs from it.
     */
    private void setTabTitles(TabLayout tabLayout) {
        ArrayList<String> titles = new ArrayList<>();
        titles.add(Constants.Tabs.MOST_EMAILED_TITLE);
        titles.add(Constants.Tabs.MOST_SHARED_TITLE);
        titles.add(Constants.Tabs.MOST_VIEWED_TITLE);
        for (int pos = 0; pos < Constants.Tabs.TABS_COUNT; pos++) {
            try {
                tabLayout
                        .getTabAt(pos)
                        .setText(titles.get(pos));

            } catch (NullPointerException e) {
                Log.e(this.getClass().getName(), e.getMessage());
            }
        }

    }
}
