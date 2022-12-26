package com.example.iotremote.assetdetail_database;

public class AssetDetail {
    int id_;
    String name;
    String id_db;
    String humidity;
    String rainfall;
    String sun_altitude;
    String sun_azimuth;
    String sun_irradiance;
    String sun_zenith;
    String temperature;
    String uv_index;
    String wind_direction;
    String wind_speed;

    public AssetDetail(String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11) {
        this.name = string;
        this.id_db = string1;
        this.humidity= string2;
        this.rainfall = string3;
        this.sun_altitude = string4;
        this.sun_azimuth= string5;
        this.sun_irradiance = string6;
        this.sun_zenith = string7;
        this.temperature = string8;
        this.uv_index= string9;
        this.wind_direction = string10;
        this.wind_speed = string11;
    }
    public int getId_() {
        return id_;
    }

    public String getName() {
        return name;
    }

    public String getId_db() {
        return id_db;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getRainfall() {
        return rainfall;
    }

    public String getSun_altitude() {
        return sun_altitude;
    }

    public String getSun_azimuth() {
        return sun_azimuth;
    }

    public String getSun_irradiance() {
        return sun_irradiance;
    }

    public String getSun_zenith() {
        return sun_zenith;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getUv_index() {
        return uv_index;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId_db(String id_db) {
        this.id_db = id_db;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public void setSun_altitude(String sun_altitude) {
        this.sun_altitude = sun_altitude;
    }

    public void setSun_azimuth(String sun_azimuth) {
        this.sun_azimuth = sun_azimuth;
    }

    public void setSun_irradiance(String sun_irradiance) {
        this.sun_irradiance = sun_irradiance;
    }

    public void setSun_zenith(String sun_zenith) {
        this.sun_zenith = sun_zenith;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }
}
