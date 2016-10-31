package com.assayah.weatherapp.DataModel;


public class WeatherResult {
    private Query query;


    public Query getQuery() {
        return query;
    }


    private boolean doesChannelExists() {
        return !(query == null || query.results == null || query.results.channel == null);
    }

    public String getTitle() {
        return doesChannelExists()?query.results.channel.getTitle():"";
    }

    public String getCityName() {
        return doesChannelExists()?query.results.channel.getLocation().getCity():"";
    }

    public Float getWindSpeed() {
        return doesChannelExists()?query.results.channel.getWind().getSpeed():0.0f;
    }

    public String getWindSpeedText() {
        return doesChannelExists()?"Wind: " + getWindSpeed().toString() + " " + getUnitSpeedText():"";
    }

    private String getUnitSpeedText() {
        return doesChannelExists()?query.results.channel.getUnits().getSpeed():"";
    }

    public int getTemperature() {
        return doesChannelExists()?query.results.channel.getItem().getCondition().getTemp():-1;
    }

    public String getTemperatureString() {
        return doesChannelExists()?"Temperature: " + String.valueOf(getTemperature()) + " " + getUnitTemperatureString():"";
    }

    private String getUnitTemperatureString() {
        return doesChannelExists()?query.results.channel.getUnits().getTemperature():"";
    }

    public String getConditionText() {
        return doesChannelExists()?"Condition: " + query.results.channel.getItem().getCondition().getText():"";
    }

    /*
    I will need to get that from the condition text but lack of time for this exercise
    public String getImageUrl() {
    }*/
}
