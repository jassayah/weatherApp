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
        return query.results.channel.getLocation().getCity();
    }

    public Float getWindSpeed() {
        return query.results.channel.getWind().getSpeed();
    }

    public String getWindSpeedText() {
        return "Wind: " + getWindSpeed().toString() + " " + getUnitSpeedText();
    }

    private String getUnitSpeedText() {
        return query.results.channel.getUnits().getSpeed();
    }

    public int getTemperature() {
        return query.results.channel.getItem().getCondition().getTemp();
    }

    public String getTemperatureString() {
        return "Temperature: " + String.valueOf(getTemperature()) + " " + getUnitTemperatureString();
    }

    private String getUnitTemperatureString() {
        return query.results.channel.getUnits().getTemperature();
    }

    public String getConditionText() {
        return "Condition: " + query.results.channel.getItem().getCondition().getText();
    }

    /*
    I will need to get that from the condition text but lack of time for this exercise
    public String getImageUrl() {
    }*/
}
