package com.example.iotremote.assetclass;

import com.google.gson.annotations.SerializedName;

public class Humidity {
    @SerializedName("type")
    private String type;
    @SerializedName("value")
    private Double value;
    @SerializedName("name")
    private String name;
    @SerializedName("meta")
    private Meta meta;
    @SerializedName("timestamp")
    private long timestamp;

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public Double getValue() {
        if (value == null)
            return -0.1;
        else
            return value;
    }

    public void setValue(Double value) {
            this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta value) {
        this.meta = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long value) {
        this.timestamp = value;
    }
}
