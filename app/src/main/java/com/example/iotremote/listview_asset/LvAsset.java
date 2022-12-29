package com.example.iotremote.listview_asset;

public class LvAsset {

    String name, ID, Status, phoneNo, country;
    int imageId;

    public LvAsset(String name, String id, String status, int imageId) {
        this.name = name;
        this.ID = id;
        this.Status = status;
//        this.phoneNo = phoneNo;
//        this.country = country;
        this.imageId = imageId;
    }
}
