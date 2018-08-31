package com.unagit.nytimesbrowser.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.unagit.nytimesbrowser.data.DataProvider;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {

    private DataProvider mDataProvider;
    private LiveData<List<Article>> mFavorites;
    private LiveData<List<Article>> mMostViewed;
    private LiveData<List<Article>> mMostEmailed;
    private LiveData<List<Article>> mMostShared;
    private LiveData<String> mErrorMessage;

    public ArticleViewModel(Application application) {
        super(application);
        mDataProvider = new DataProvider(application);
        mFavorites = mDataProvider.getFavorites();
        mMostEmailed = mDataProvider.getMostEmailed();
        mMostViewed = mDataProvider.getMostViewed();
        mMostShared = mDataProvider.getMostShared();
        mErrorMessage = mDataProvider.getErrorMessage();
    }

    public LiveData<List<Article>> getFavorites() {
        return mFavorites;
    }

    public LiveData<List<Article>> getMostEmailed() {
        return mMostEmailed;
    }

    public LiveData<List<Article>> getMostShared() {
        return mMostShared;
    }

    public LiveData<List<Article>> getMostViewed() {
        return mMostViewed;
    }

    public void insertFavorite(Article article) {
        mDataProvider.insertFavorite(article);
    }

    public void deleteFavorite(Article article) {
        mDataProvider.deleteFavorite(article);
    }

    public LiveData<String> getErrorMessage() { return mErrorMessage; }
}


