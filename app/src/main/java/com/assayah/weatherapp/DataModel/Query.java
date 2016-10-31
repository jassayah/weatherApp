package com.assayah.weatherapp.DataModel;

public class Query {

    public int count;
    public String created;
    public String lang;
    public Results results;

    public class Results {
        Channel channel;
    }
}