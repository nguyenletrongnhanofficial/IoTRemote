package com.example.iotremote.assetdetail_database;

public class AssetDetail {
    int id_;
    String name;
    String id_asset;
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
    String date;

    public AssetDetail(String name, String id_asset, String humidity, String railfall, String sunal, String sunaz, String sunir, String sunze, String temp, String uv, String winddir, String windsp, String date) {
        this.name = name;
        this.id_asset = id_asset;
        this.humidity= humidity;
        this.rainfall = railfall;
        this.sun_altitude = sunal;
        this.sun_azimuth= sunaz;
        this.sun_irradiance = sunir;
        this.sun_zenith = sunze;
        this.temperature = temp;
        this.uv_index= uv;
        this.wind_direction = winddir;
        this.wind_speed = windsp;
        this.date = date;
    }
    public int getId_() {
        return id_;
    }

    public String getName() {
        return name;
    }

    public String getId_asset() {
        return id_asset;
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

    public void setId_asset(String id_asset) {
        this.id_asset = id_asset;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
