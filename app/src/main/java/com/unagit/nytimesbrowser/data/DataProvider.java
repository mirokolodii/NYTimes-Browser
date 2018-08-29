package com.unagit.nytimesbrowser.data;

import android.content.Context;
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

    public interface CallbackResult {
        void onCallbackResultsReceived(List<Article> results);
    }

    //    private List<Article> articles;
    private CallbackResult callback;
    private Context context;


    public void fetchData(CallbackResult callback, Context context, int queryType) {
        Log.d(this.getClass().getSimpleName(), "fetchData is triggered");
//        Log.d(this.getClass().getSimpleName(), "queryType: " + String.valueOf(queryType));

        this.callback = callback;
        this.context = context;

//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Retrofit.NYTBaseApiUrl)
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NYTAPIService nytapiService = retrofit.create(NYTAPIService.class);
        Call<DataWrapper> call;
        switch (queryType) {
            case Constants.Tabs.MOST_EMAILED_TAB:
                call = nytapiService.getMostEmailed(Constants.Retrofit.NYTApiKey);
                break;
            case Constants.Tabs.MOST_SHARED_TAB:
                call = nytapiService.getMostShared(Constants.Retrofit.NYTApiKey);
                break;
            case Constants.Tabs.MOST_VIEWED_TAB:
                call = nytapiService.getMostViewed(Constants.Retrofit.NYTApiKey);
                break;

            case Constants.Tabs.FAVORITES_TAB:
                getFavorites();
                return;

            default:
                call = nytapiService.getMostEmailed(Constants.Retrofit.NYTApiKey);
        }

        Log.d(this.getClass().getSimpleName(), call.request().url().toString());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DataWrapper> call, Response<DataWrapper> response) {
        Log.d(this.getClass().getSimpleName(), "onResponse is triggered");
        if (response.isSuccessful()) {
            DataWrapper dataWrapper = response.body();
            if (dataWrapper != null && callback != null) {
                for(Article article : dataWrapper.getArticles()) {
                    article.generateId();
                    Log.d(DataProvider.this.getClass().getSimpleName(), article.getUrl());
                    Log.d(DataProvider.this.getClass().getSimpleName(), "ID: " + article.getId());

                }
                callback.onCallbackResultsReceived(dataWrapper.getArticles());
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
        Toast.makeText(this.context, "Unable to get articles due to technical issues.", Toast.LENGTH_SHORT).show();
    }

    public void getFavorites() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalDatabase db = LocalDatabase.getLocalDBInstance(DataProvider.this.context);
                List<Article> favorites = db.articleDao().getFavorites();
                DataProvider.this.callback.onCallbackResultsReceived(favorites);
//                for(Article favorite : favorites) {
//                    Log.d("Favorites:", favorite.getUrl());
//                }
            }
        }).start();
    }


}
