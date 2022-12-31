package com.example.iotremote.chart_database.chart;

public class AssetDailyData {
    int id_;
    String name;
    String id_asset;
    String humidity;
    String temperature;
    String wind_direction;
    String wind_speed;
    String day;
    String month;
    String year;
    public AssetDailyData(String name, String id_asset, String humidity, String temp, String winddir, String windsp, String day, String month, String year) {
        this.name = name;
        this.id_asset = id_asset;
        this.humidity= humidity;
        this.temperature = temp;
        this.wind_direction = winddir;
        this.wind_speed = windsp;
        this.day = day;
        this.month = month;
        this.year = year;
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

    public String getTemperature() {
        return temperature;
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

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }
    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
