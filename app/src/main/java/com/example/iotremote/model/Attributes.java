package com.example.iotremote.model;

import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("location")
    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    Location location;
}
