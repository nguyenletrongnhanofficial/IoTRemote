package com.example.iotremote.assetclass;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AssetCs{

    @SerializedName("id")
    public String getId() {
        return this.id; }
    public void setId(String id) {
        this.id = id; }
    String id;
    @SerializedName("version")
    public int getVersion() {
        return this.version; }
    public void setVersion(int version) {
        this.version = version; }
    int version;
    @SerializedName("createdOn")
    public long getCreatedOn() {
        return this.createdOn; }
    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn; }
    long createdOn;
    @SerializedName("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
    @SerializedName("accessPublicRead")
    public boolean getAccessPublicRead() {
        return this.accessPublicRead; }
    public void setAccessPublicRead(boolean accessPublicRead) {
        this.accessPublicRead = accessPublicRead; }
    boolean accessPublicRead;
    @SerializedName("parentId")
    public String getParentId() {
        return this.parentId; }
    public void setParentId(String parentId) {
        this.parentId = parentId; }
    String parentId;
    @SerializedName("realm")
    public String getRealm() {
        return this.realm; }
    public void setRealm(String realm) {
        this.realm = realm; }
    String realm;
    @SerializedName("type")
    public String getType() {
        return this.type; }
    public void setType(String type) {
        this.type = type; }
    String type;
    @SerializedName("path")
    public ArrayList<String> getPath() {
        return this.path; }
    public void setPath(ArrayList<String> path) {
        this.path = path; }
    ArrayList<String> path;
    @SerializedName("attributes")
    public Attributes getAttributes() {
        return this.attributes; }
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes; }
    Attributes attributes;

    public AssetCs(String name, String id_asset, String humidity, String railfall, String sunal, String sunaz, String sunir, String sunze, String temp, String uv, String winddir, String windsp, ArrayList <Float> location) {
        this.setName(name);
        this.setId(id_asset);
        Attributes attri_ = new Attributes();
        Humidity hum_ = new Humidity();
        Humidity rain_ = new Humidity();
        Humidity sunal_ = new Humidity();
        Humidity sunaz_ = new Humidity();
        Humidity sunir_ = new Humidity();
        Humidity sunze_ = new Humidity();
        Humidity temp_ = new Humidity();
        Humidity uv_ = new Humidity();
        Humidity wind_ = new Humidity();
        Humidity windsp_ = new Humidity();
        hum_.setValue(Double.parseDouble(humidity));
        attri_.setHumidity(hum_);
        rain_.setValue(Double.parseDouble(railfall));
        attri_.setRainfall(rain_);
        sunal_.setValue(Double.parseDouble(sunal));
        attri_.setSunAltitude(sunal_);
        sunaz_.setValue(Double.parseDouble(sunaz));
        attri_.setSunAzimuth(sunaz_);
        sunir_.setValue(Double.parseDouble(sunir));
        attri_.setSunIrradiance(sunir_);
        sunze_.setValue(Double.parseDouble(sunze));
        attri_.setSunZenith(sunze_);
        temp_.setValue(Double.parseDouble(temp));
        attri_.setTemperature(temp_);
        uv_.setValue(Double.parseDouble(uv));
        attri_.setUVIndex(uv_);
        wind_.setValue(Double.parseDouble(winddir));
        attri_.setWindDirection(wind_);
        windsp_.setValue(Double.parseDouble(windsp));
        attri_.setWindSpeed(windsp_);

        Location loca = new Location();
        Value val = new Value();
        val.setCoordinates(location);
        loca.setValue(val);
        attri_.setLocation(loca);
        this.setAttributes(attri_);
    }
}
