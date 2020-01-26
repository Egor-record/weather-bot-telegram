package com.telegram;

import org.json.simple.JSONObject;

public class Weather  {

    public double temp;

    public long humidity;

    public double feels_like;

    public double pressure;

    public double wind;

    public long speed;


    public void getRequestFromAPI(Float lat, Float lon) throws Exception {

        UrlRequester request = new UrlRequester();

        JSONObject weather = (JSONObject) request.getWeather(lat, lon);

        JSONObject main = (JSONObject) weather.get("main");

        setTemp((double) main.get("temp"));


    }


    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp() {
        return temp;
    }


}
