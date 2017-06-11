package com.example.testapplication.network;

/**
 * API Endpoints
 */

import com.example.testapplication.pojo.Categories;
import com.example.testapplication.pojo.Offers;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ServerAPI {

    String ENDPOINT = "http://ufa.farfor.ru/";

    @GET("getyml/?")
    Observable<Offers> getItems(@Query("key") String key);

    @GET("getyml/?")
    Observable<Categories> getCategories(@Query("key") String key);
}
