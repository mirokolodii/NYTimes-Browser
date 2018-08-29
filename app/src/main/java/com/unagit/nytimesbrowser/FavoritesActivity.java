package com.unagit.nytimesbrowser;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.unagit.nytimesbrowser.data.DataProvider;
import com.unagit.nytimesbrowser.helpers.Constants;
import com.unagit.nytimesbrowser.models.Article;
import com.unagit.nytimesbrowser.models.ArticleViewModel;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private ArticleViewModel mArticleViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add back button on toolbar
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(this.getClass().getSimpleName(), e.getMessage());
        }

        final MainListAdapter adapter = new MainListAdapter(this);
        ListView listView = findViewById(R.id.articles_list_view);
        listView.setAdapter(adapter);

        mArticleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        mArticleViewModel.getFavorites().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                adapter.setFavorites(articles);
                adapter.setArticles(articles);
            }
        });
    }
}
