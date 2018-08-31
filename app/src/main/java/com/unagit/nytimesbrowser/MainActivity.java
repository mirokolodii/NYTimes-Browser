package com.unagit.nytimesbrowser;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.unagit.nytimesbrowser.helpers.Constants;
import com.unagit.nytimesbrowser.models.Article;
import com.unagit.nytimesbrowser.models.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArticleViewModel mArticleViewModel;
    private ProgressBar mProgressBar;

    @Override
    protected void onPause() {
        super.onPause();
        showProgressBar(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        mProgressBar = findViewById(R.id.progressBar);

        // Set tabs and show them on screen, using ViewPager.
        setupViewPagerAndTabLayout();

        mArticleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fav_menu_btn:
                launchFavoritesActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

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

    private void launchFavoritesActivity() {
        startActivity(new Intent(this, FavoritesActivity.class));
    }

    public void showProgressBar(boolean show) {
        if(mProgressBar != null) {
            if(show){
                mProgressBar.setVisibility(View.VISIBLE);
            } else {
                mProgressBar.setVisibility(View.GONE);
            }
        }
    }
}
