package com.example.iotremote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Default {
    @SerializedName("bounds")
    @Expose
    private ArrayList<Float> bounds;
    @SerializedName("centers")
    @Expose
    private ArrayList<Float> centers;

    public ArrayList<Float> getBounds() {
        return bounds;
    }
    public void setBounds(ArrayList<Float> bounds) {
        this.bounds = bounds;
    }
    public ArrayList<Float> getCenters() {
        return centers;
    }
    public void setCenters(ArrayList<Float> centers) {
        this.centers = centers;
    }
}