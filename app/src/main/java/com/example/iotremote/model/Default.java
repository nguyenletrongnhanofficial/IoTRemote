package com.example.iotremote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Default {
    @SerializedName("bounds")
    @Expose
    private List<Float> bounds;
    @SerializedName("centers")
    @Expose
    private List<Float> centers;

    public List<Float> getBounds() {
        return bounds;
    }
    public void setBounds(List<Float> bounds) {
        this.bounds = bounds;
    }
    public List<Float> getCenters() {
        return centers;
    }
    public void setCenters(List<Float> centers) {
        this.centers = centers;
    }
}