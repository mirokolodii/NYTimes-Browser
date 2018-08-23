package com.unagit.nytimesbrowser.Data;

import android.util.Log;

import com.unagit.nytimesbrowser.helpers.Constants;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestDataProvider implements Callback<ResponseBody> {

    public void start() {
        Log.d(this.getClass().getSimpleName(), "start is triggered");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Retrofit.NYTBaseApiUrl)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NYTAPIService nytapiService = retrofit.create(NYTAPIService.class);
        Call<ResponseBody> call = nytapiService.getRawJSON(Constants.Retrofit.NYTApiKey);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.d(this.getClass().getSimpleName(), "onResponse is triggered");

        try {
            String result = response.body().string();
            Log.d(this.getClass().getSimpleName(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.d(this.getClass().getSimpleName(), "onFailure is triggered");
        t.printStackTrace();
    }
}
