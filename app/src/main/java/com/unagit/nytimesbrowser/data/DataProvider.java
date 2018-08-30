package com.unagit.nytimesbrowser.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unagit.nytimesbrowser.models.Article;
import com.unagit.nytimesbrowser.models.DataWrapper;
import com.unagit.nytimesbrowser.helpers.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataProvider implements Callback<DataWrapper> {

    private static LocalDatabase mDB;
    private Application mApplication;
    private Call<DataWrapper> mCall;
    private NYTAPIService mNytapiService;

    private MutableLiveData<List<Article>> mArticles = new MutableLiveData<>();
    private MutableLiveData<List<Article>> mMostViewed = new MutableLiveData<>();
    private MutableLiveData<List<Article>> mMostEmailed = new MutableLiveData<>();
    private MutableLiveData<List<Article>> mMostShared = new MutableLiveData<>();

    public DataProvider(Application application) {
        mDB = LocalDatabase.getLocalDBInstance(application);
        mApplication = application;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Retrofit.NYTBaseApiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         mNytapiService = retrofit.create(NYTAPIService.class);
    }

    public LiveData<List<Article>> getMostEmailed() {
//        mCall = mNytapiService.getMostEmailed(Constants.Retrofit.NYTApiKey);
//        Log.d(this.getClass().getSimpleName(), mCall.request().url().toString());
//        mCall.enqueue(this);
        return mArticles;
//        return mMostEmailed;
    }

    public LiveData<List<Article>> getMostShared() {
//        mCall = mNytapiService.getMostShared(Constants.Retrofit.NYTApiKey);
//        Log.d(this.getClass().getSimpleName(), mCall.request().url().toString());
//        mCall.enqueue(this);
        return mArticles;
//        return mMostShared;
    }

    public LiveData<List<Article>> getMostViewed() {
        mCall = mNytapiService.getMostViewed(Constants.Retrofit.NYTApiKey);
        Log.d(this.getClass().getSimpleName(), mCall.request().url().toString());
//        mCall.enqueue(this);
//        return mArticles;
        MostViewedReceiver mostViewedReceiver = new MostViewedReceiver();
        mCall.enqueue(mostViewedReceiver);
        return mMostViewed;
    }

    private class MostViewedReceiver implements Callback<DataWrapper> {

        @Override
        public void onResponse(Call<DataWrapper> call, Response<DataWrapper> response) {
            if (response.isSuccessful()) {
                DataWrapper dataWrapper = response.body();
                if (dataWrapper != null) {
                    List<Article> articles = dataWrapper.getArticles();
                    for(Article article : articles) {
                        article.generateId();
                    }
                    DataProvider.this.mMostViewed.setValue(articles);
                } else {
                    DataProvider.this.showErrorToast();
                }
            } else {
                System.out.println(response.errorBody());
                showErrorToast();
            }
        }

        @Override
        public void onFailure(Call<DataWrapper> call, Throwable t) {

        }
    }


    @Override
    public void onResponse(Call<DataWrapper> call, Response<DataWrapper> response) {
        Log.d(this.getClass().getSimpleName(), "onResponse is triggered");
        if (response.isSuccessful()) {
            DataWrapper dataWrapper = response.body();
            if (dataWrapper != null) {
                List<Article> articles = dataWrapper.getArticles();
                for(Article article : articles) {
                    article.generateId();
                    Log.d(DataProvider.this.getClass().getSimpleName(), article.getUrl());
                    Log.d(DataProvider.this.getClass().getSimpleName(), "ID: " + article.getId());
                }
                mArticles.setValue(articles);
            } else {
                showErrorToast();
            }
        } else {
            System.out.println(response.errorBody());
            showErrorToast();
        }
    }

    @Override
    public void onFailure(Call<DataWrapper> call, Throwable t) {
        Log.d(this.getClass().getSimpleName(), "onFailure is triggered");
        t.printStackTrace();
        showErrorToast();
    }

    //TODO: design a better solution to separate UI from data providers.
    private void showErrorToast() {
        Toast.makeText(mApplication, "Unable to get articles due to technical issues.", Toast.LENGTH_SHORT).show();
    }

    public LiveData<List<Article>> getFavorites() {
        return mDB.articleDao().getFavorites();
    }

    public void insertFavorite(Article article) {
        new InsertAsyncTask().execute(article);
    }

    public void deleteFavorite(Article article) {
        new DeleteAsyncTask().execute(article);
    }

    private static class InsertAsyncTask extends AsyncTask<Article, Void, Void> {
        @Override
        protected Void doInBackground(Article... articles) {
            mDB.articleDao().insert(articles[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Article, Void, Void> {
        @Override
        protected Void doInBackground(Article... articles) {
            mDB.articleDao().delete(articles[0]);
            return null;
        }
    }
}
