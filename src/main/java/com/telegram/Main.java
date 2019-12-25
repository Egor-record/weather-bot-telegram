package com.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Main {

        public static void main(String[] args) throws Exception {

            Weather weather = new Weather();

            weather.getRequestFromAPI();

        }
}
