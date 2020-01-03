package com.telegram;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Weather {

    public String description;

    public double temp;

    public double humidity;

    public String wind;

    public double sunset;

    public double sunrise;


    public void getRequestFromAPI() throws Exception {

        UrlRequester request = new UrlRequester();

        JSONObject weather = (JSONObject) request.getWeather();

        System.out.println(weather.get("main"));


    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }
}
