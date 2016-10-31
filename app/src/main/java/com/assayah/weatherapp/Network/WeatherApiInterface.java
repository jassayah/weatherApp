package com.assayah.weatherapp.Network;


import com.assayah.weatherapp.DataModel.WeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {

    @GET("yql?format=json&env=store://datatables.org/alltableswithkeys")
    Call<WeatherResult> getYahooWeather(@Query("q") String q);
}
