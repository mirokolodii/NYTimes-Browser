package com.unagit.nytimesbrowser.Data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unagit.nytimesbrowser.helpers.Constants;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataProvider implements Callback<DataWrapper> {

    private List<Article> articles;

    public void fetchData() {
        Log.d(this.getClass().getSimpleName(), "fetchData is triggered");
//        this.articles = articles;

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

//        Call<Object> call = nytapiService.getJSON(Constants.Retrofit.NYTApiKey);
//        call.enqueue(new Callback<Object>() {
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.d(this.getClass().getSimpleName(), "onResponse");
//                Log.d(this.getClass().getSimpleName(), new Gson().toJson(response.body()));
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Log.d(this.getClass().getSimpleName(), "onFailure");
//                Log.d(this.getClass().getSimpleName(), "onFailure: " + t.toString());
//            }
//        });
        Log.d(this.getClass().getSimpleName(), call.request().url().toString());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DataWrapper> call, Response<DataWrapper> response) {
        Log.d(this.getClass().getSimpleName(), "onResponse is triggered");
        if (response.isSuccessful()) {
            DataWrapper changesList = response.body();
            Log.d(this.getClass().getSimpleName(), changesList.getArticles().toString());
//            for (int i = 0; i < changesList.size(); i++) {
//                Log.d(this.getClass().getSimpleName(), changesList.get(i).getTitle());
//            }
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
