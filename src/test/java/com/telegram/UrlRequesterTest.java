package com.telegram;

import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

class UrlRequesterTest {

    private UrlRequester sender;

    @Before
    public void initTest() {
        sender = new UrlRequester();
    }

    @org.junit.jupiter.api.Test
    void sendGet() throws Exception {
        assertEquals(662, sender.sendGet("?lat=35&lon=139").length());

    }
}