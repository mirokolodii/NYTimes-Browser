package com.unagit.nytimesbrowser.data;

import com.unagit.nytimesbrowser.models.Article;
import com.unagit.nytimesbrowser.models.DataWrapper;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTAPIService {

    @GET("mostemailed/all-sections/30.json")
    Call<DataWrapper> getMostEmailed(@Query("api-key") String apiKey);

    @GET("mostshared/all-sections/30.json")
    Call<DataWrapper> getMostShared(@Query("api-key") String apiKey);

    @GET("mostviewed/all-sections/30.json")
    Call<DataWrapper> getMostViewed(@Query("api-key") String apiKey);

    @GET("mostviewed/all-sections/30.json")
    Call<ResponseBody> getRawJSON(@Query("api-key") String apiKey);

}
