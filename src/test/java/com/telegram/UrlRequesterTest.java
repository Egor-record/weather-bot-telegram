package com.telegram;

import static org.junit.jupiter.api.Assertions.*;

class UrlRequesterTest {

    private UrlRequester sender;

    @org.junit.jupiter.api.Test
    void sendGet() throws Exception {

        sender = new UrlRequester();

        assertEquals(464, sender.sendGet("lat=35&lon=139").length());
    }
}