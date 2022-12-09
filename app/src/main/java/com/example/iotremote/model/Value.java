package com.example.iotremote.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
public class Value{
    @SerializedName("coordinates")
    public ArrayList<Float> getCoordinates() {
        return this.coordinates; }
    public void setCoordinates(ArrayList<Float> coordinates) {
        this.coordinates = coordinates; }
    ArrayList<Float> coordinates;
    @SerializedName("type")
    public String getType() {
        return this.type; }
    public void setType(String type) {
        this.type = type; }
    String type;
}