package com.example.iotremote.assetclass;

import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("sunIrradiance")
    private Humidity sunIrradiance;
    @SerializedName("rainfall")
    private Humidity rainfall;
    @SerializedName("notes")
    private Humidity notes;
    @SerializedName("uVIndex")
    private Humidity uVIndex;
    @SerializedName("sunAzimuth")
    private Humidity sunAzimuth;
    @SerializedName("sunZenith")
    private Humidity sunZenith;
    @SerializedName("temperature")
    private Humidity temperature;
    @SerializedName("humidity")
    private Humidity humidity;
    @SerializedName("location")
    private Location location;
    @SerializedName("windDirection")
    private Humidity windDirection;
    @SerializedName("windSpeed")
    private Humidity windSpeed;
    @SerializedName("sunAltitude")
    private Humidity sunAltitude;

    public Humidity getSunIrradiance() { return sunIrradiance; }
    public void setSunIrradiance(Humidity value) { this.sunIrradiance = value; }

    public Humidity getRainfall() { return rainfall; }
    public void setRainfall(Humidity value) { this.rainfall = value; }

    public Humidity getNotes() { return notes; }
    public void setNotes(Humidity value) { this.notes = value; }

    public Humidity getUVIndex() { return uVIndex; }
    public void setUVIndex(Humidity value) { this.uVIndex = value; }

    public Humidity getSunAzimuth() { return sunAzimuth; }
    public void setSunAzimuth(Humidity value) { this.sunAzimuth = value; }

    public Humidity getSunZenith() { return sunZenith; }
    public void setSunZenith(Humidity value) { this.sunZenith = value; }

    public Humidity getTemperature() { return temperature; }
    public void setTemperature(Humidity value) { this.temperature = value; }

    public Humidity getHumidity() { return humidity; }
    public void setHumidity(Humidity value) { this.humidity = value; }

    public Location getLocation() { return location; }
    public void setLocation(Location value) { this.location = value; }

    public Humidity getWindDirection() { return windDirection; }
    public void setWindDirection(Humidity value) { this.windDirection = value; }

    public Humidity getWindSpeed() { return windSpeed; }
    public void setWindSpeed(Humidity value) { this.windSpeed = value; }

    public Humidity getSunAltitude() { return sunAltitude; }
    public void setSunAltitude(Humidity value) { this.sunAltitude = value; }
}
