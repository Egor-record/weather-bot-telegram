package com.telegram;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;


public class UrlRequester {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();


    public void close() throws IOException {
        httpClient.close();
    }

    /**
     *
     * @param url - url to API + params + public keys
     * @return String that returns you weather API
     * @throws Exception throws if no network
     */
    public String sendGet(String url) throws Exception {

        //

        HttpGet request = new HttpGet(url);


        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

                // return it as a String
            String result = EntityUtils.toString(entity);

            return result;

        }

    }

    /***
     *
     * @param json - string with json
     * @return Forecast class with object main which contains temp, feels_like, temp_min
     */
    public Object handleJSON(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(json);
    }

    public Object getWeather(Float lat, Float lon) throws Exception {
        try {
            return handleJSON(sendGet("https://api.openweathermap.org/data/2.5/weather?lat=" + lat.toString() + "&lon=" + lon.toString() + "&units=metric&appid=" + Keys.Weather_Key));
        } finally {
            close();
        }
    }



}
