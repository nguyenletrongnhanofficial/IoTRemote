package com.example.iotremote.model;

import com.google.gson.annotations.SerializedName;

public class Options {

    public Default getDefaults() {
        return defaults;
    }

    public void setDefaults(Default defaults) {
        this.defaults = defaults;
    }

    @SerializedName("default")
    private Default defaults;
}
