package com.unagit.nytimesbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.unagit.nytimesbrowser.data.DataProvider;
import com.unagit.nytimesbrowser.helpers.Constants;
import com.unagit.nytimesbrowser.models.Article;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements DataProvider.CallbackResult{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add back button to activity on toolbar
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(this.getClass().getSimpleName(), e.getMessage());
        }

        DataProvider provider = new DataProvider();
        provider.fetchData(this, this, Constants.Tabs.FAVORITES_TAB);





    }


    @Override
    public void onCallbackResultsReceived(List<Article> results) {
        MainListAdapter adapter = new MainListAdapter(FavoritesActivity.this, results);
        ListView listView = findViewById(R.id.articles_list_view);
        listView.setAdapter(adapter);
    }
}
