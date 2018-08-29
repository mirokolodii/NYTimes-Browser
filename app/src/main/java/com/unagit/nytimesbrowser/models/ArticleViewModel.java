package com.unagit.nytimesbrowser.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.unagit.nytimesbrowser.data.DataProvider;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {

    private DataProvider mDataProvider;
    private LiveData<List<Article>> mArticles;

    public ArticleViewModel(Application application) {
        super(application);
        mDataProvider = new DataProvider();
        mArticles = mDataProvider.getFavorites();

    }

    public LiveData<List<Article>> getFavorites() {
        return mArticles;
    }

    public void insertFavorite(Article article) {
        mDataProvider.insertFavorite(article);
    }

    public void deleteFavorite(Article article) {
        mDataProvider.deleteFavorite(article);
    }
}


