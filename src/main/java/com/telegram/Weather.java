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

        JSONObject wind = (JSONObject) weather.get("wind");

        setTemp((double) main.get("temp"));

        setWind((double) wind.get("speed"));
//
//        setFeels_like((double) main.get("feels_like"));
//
//        setHumidity((long) main.get("humidity"));

    }


    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getTemp() {
        return temp;
    }

    public double getWind() {
        return wind;
    }

    public void setSpeed(long speed) {this.speed = speed; };

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public double getPressure() {
        return pressure;
    }

    public long getSpeed() {return speed; }

}
