package com.example.iotremote.api;

import com.example.iotremote.assetclass.AssetCs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AssetAPI {
    AssetAPI assetAPI = APIClient.getClient().create(AssetAPI.class);

    @GET("api/master/asset/{assetID}")
    Call<AssetCs> getAsset(@Path("assetID") String assetID);//, @Header("Authorization") String auth);
}
