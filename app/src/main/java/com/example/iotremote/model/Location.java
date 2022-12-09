package com.example.iotremote.model;

import com.google.gson.annotations.SerializedName;

public class Location{
    @SerializedName("type")
    public String getType() {
        return this.type; }
    public void setType(String type) {
        this.type = type; }
    String type;
    @SerializedName("value")
    public Value getValue() {
        return this.value; }
    public void setValue(Value value) {
        this.value = value; }
    Value value;
    @SerializedName("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
    @SerializedName("timestamp")
    public long getTimestamp() {
        return this.timestamp; }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp; }
    long timestamp;
}