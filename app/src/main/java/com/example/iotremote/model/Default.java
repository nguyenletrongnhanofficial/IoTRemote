package com.example.iotremote.model;

import java.util.ArrayList;
import java.util.List;

public class Default {

    public List<Bounds> getBounds() {
        return bounds;
    }

    public void setBounds(List<Bounds> bounds) {
        this.bounds = bounds;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public float getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(float maxZoom) {
        this.maxZoom = maxZoom;
    }

    public boolean isBoxZoom() {
        return boxZoom;
    }

    public void setBoxZoom(boolean boxZoom) {
        this.boxZoom = boxZoom;
    }

    public String getGeocodeUrl() {
        return geocodeUrl;
    }

    public void setGeocodeUrl(String geocodeUrl) {
        this.geocodeUrl = geocodeUrl;
    }

    public String getMinZoom() {
        return minZoom;
    }

    public void setMinZoom(String minZoom) {
        this.minZoom = minZoom;
    }


    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

    private List<Center>centers;
    private List<Bounds> bounds;
    private float zoom;
    private float maxZoom;
    private boolean boxZoom;
    private String geocodeUrl;
    private String minZoom;
}
