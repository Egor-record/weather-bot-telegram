package com.telegram;

public class Weather {

    public String description;

    public double temp;

    public String wind;

    public double sunset;

    public double sunrise;


    public void getRequestFromAPI() throws Exception {

        UrlRequester obj = new UrlRequester();

        try {

            obj.sendGet("lat=35&lon=139");


        } finally {
            obj.close();
        }

    }



}
