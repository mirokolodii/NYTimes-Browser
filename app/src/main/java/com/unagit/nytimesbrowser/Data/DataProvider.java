package com.unagit.nytimesbrowser.Data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unagit.nytimesbrowser.Models.Article;
import com.unagit.nytimesbrowser.Models.DataWrapper;
import com.unagit.nytimesbrowser.helpers.Constants;

import java.util.ArrayList;
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


    public void fetchData(CallbackResult callback) {
        Log.d(this.getClass().getSimpleName(), "fetchData is triggered");

        this.callback = callback;

        Gson gson = new GsonBuilder()
//                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Retrofit.NYTBaseApiUrl)
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NYTAPIService nytapiService = retrofit.create(NYTAPIService.class);
        Call<DataWrapper> call = nytapiService.getMostEmailed(Constants.Retrofit.NYTApiKey);
        Log.d(this.getClass().getSimpleName(), call.request().url().toString());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DataWrapper> call, Response<DataWrapper> response) {
        Log.d(this.getClass().getSimpleName(), "onResponse is triggered");
        if (response.isSuccessful()) {
            DataWrapper dataWrapper = response.body();
            if(dataWrapper != null && callback != null) {
                callback.onCallbackResultsReceived(dataWrapper.getArticles());
            }
//            Article article = (dataWrapper.getArticles()).get(0);
//            Log.d(this.getClass().getSimpleName(), article.getTitle());

        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<DataWrapper> call, Throwable t) {
        Log.d(this.getClass().getSimpleName(), "onFailure is triggered");
        t.printStackTrace();
    }


}
