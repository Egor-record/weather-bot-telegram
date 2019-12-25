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
            System.out.println("Testing 1 - Send Http GET request");
            obj.sendGet();


        } finally {
            obj.close();
        }

    }



}
