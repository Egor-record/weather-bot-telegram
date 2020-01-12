package com.telegram;



import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


public class Main {

        public static void main(String[] args) throws Exception {

          //  Weather weather = new Weather();

           // weather.getRequestFromAPI();

            ApiContextInitializer.init();

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            try {
                telegramBotsApi.registerBot(new Weather_bot());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }
}
