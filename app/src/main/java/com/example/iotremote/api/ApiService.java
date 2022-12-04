package com.example.iotremote.api;

import com.example.iotremote.model.Center;
import com.example.iotremote.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

//    ApiService apiService = new Retrofit.Builder()
//            .baseUrl("https://103.126.161.199/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService.class);

    ApiService apiService =  APIClient.getClient().create(ApiService.class);


    @GET("api/master/map/js")
    Call<Currency> loadMap();
}
