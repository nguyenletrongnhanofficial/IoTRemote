package com.example.iotremote.api;

import com.example.iotremote.mapclass.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.http.GET;

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
