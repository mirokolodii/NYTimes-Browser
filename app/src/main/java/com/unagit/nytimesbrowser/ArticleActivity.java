package com.unagit.nytimesbrowser;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.unagit.nytimesbrowser.helpers.Constants;
import com.unagit.nytimesbrowser.models.Article;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add back button to activity on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String articleUrl = getUrl();
        if(articleUrl != null && !articleUrl.isEmpty()) {
            showArticle(articleUrl);
        }
    }

    private String getUrl() {
        String url = this.getIntent().getStringExtra(Constants.IntentArticleExtras.articleUrl);
        return url;
    }

    private void showArticle(String url) {
        WebView webView = findViewById(R.id.article_webview);
        webView.loadUrl(url);
    }
}
