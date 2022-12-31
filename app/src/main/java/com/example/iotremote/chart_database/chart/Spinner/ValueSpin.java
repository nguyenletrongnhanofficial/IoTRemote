package com.example.iotremote.chart_database.chart.Spinner;

import com.example.iotremote.R;

public enum ValueSpin {
    ThumbnailValue1("Humidity (%)", R.drawable.vector__6_),
    ThumbnailValue2("Temperature (°C)", R.drawable.ic_baseline_severe_cold_24),
    ThumbnailValue3("Wind direction", R.drawable.ic_baseline_sailing_24),
    ThumbnailValue4("Wind speed (km/h)", R.drawable.ic_baseline_speed_24);
    private String name;
    private int img;

    ValueSpin(String name, int img) {
        this.name = name;
        this.img = img;
    }
    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int setThumbnail (String name){
        if (name == this.name){
            return (this.img);
        }
        return 0;
    }
}
