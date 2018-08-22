package com.unagit.nytimesbrowser.Data;

import com.unagit.nytimesbrowser.BuildConfig;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTAPIService {

    @GET("mostemailed/all-sections/30.json")
    Call<List<Article>> getMostEmailed(@Query("api-key") String apiKey);

    @GET("mostshared/all-sections/30.json")
    Call<List<Article>> getMostShared(@Query("api-key") String apiKey);

    @GET("mostviewed/all-sections/30.json")
    Call<List<Article>> getMostViewed(@Query("api-key") String apiKey);

}