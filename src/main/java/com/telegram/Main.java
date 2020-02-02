package com.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Main {


        public static void main(String[] args) {

            ApiContextInitializer.init();

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            try {

                Weather_bot bot = new Weather_bot();

                telegramBotsApi.registerBot(bot);

                Thread t = new Thread(() -> {

                    while(true) {

                        try {

                            Thread.sleep(1000*60*60);

                            bot.sendForecastByTimer();


                        } catch (InterruptedException ie) {
                            System.out.println("Timer exception");
                        }
                    }
                });
                t.start();

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }
}
