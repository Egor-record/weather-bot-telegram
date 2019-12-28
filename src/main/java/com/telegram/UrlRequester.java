package com.telegram;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import java.io.IOException;


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

        HttpGet request = new HttpGet("https://api.openweathermap.org/data/2.5/weather?" + url + "&appid=" + Keys.Weather_Key);
        // add request headers
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();

            System.out.println(headers);

                // return it as a String
            String result = EntityUtils.toString(entity);
            System.out.println(result);

            return result;


        }

    }

}
