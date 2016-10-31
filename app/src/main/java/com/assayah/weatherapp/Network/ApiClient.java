package com.assayah.weatherapp.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jassayah on 10/28/16.
 */
public class ApiClient {

    public static final String BASE_URL = "https://query.yahooapis.com/v1/public/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static String buildQuery(String city) {
        return "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" + city + "\")";
    }
}