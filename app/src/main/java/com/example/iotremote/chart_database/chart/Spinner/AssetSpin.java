package com.example.iotremote.chart_database.chart.Spinner;

import com.example.iotremote.R;

public enum AssetSpin {
    ThumbnailAsset1("Weather Asset", R.drawable.vector__5_),
    ThumbnailAsset2("Weather Asset 2", R.drawable.vector__5_),
    ThumbnailAsset3("Weather Asset 3", R.drawable.vector__5_),;
    private String name;
    private int img;

    AssetSpin(String name, int img) {
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
