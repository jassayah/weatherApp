package com.assayah.weatherapp.DataModel;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Item {
    private String title;
    private String link;
    private String description;

    @SerializedName("lat")
    private float geoLat;
    @SerializedName("long")
    private float geoLong;
    private Condition condition;
    private ArrayList<Forecast> forecasts;


    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public Condition getCondition() {
        return condition;
    }

    public ArrayList<Forecast> getForecasts() {
        return forecasts;
    }

    public float getLatitude() {
        return geoLat;
    }

    public float getLongitude() {
        return geoLong;
    }


}
