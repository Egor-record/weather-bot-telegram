package com.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * Weather bot class which describes bots main methods.
 *
 * **/
public class Weather_bot extends TelegramLongPollingBot {

    /**
     * This method runs when you receive message from Telegram. Each request from Telegram runs in separate thead
     */
    @Override
    public void onUpdateReceived(Update update) {

        Thread tread = new Thread(() -> {

        System.out.println(update.getMessage().getText());

            SendMessage message = new SendMessage();

            /*
              Check whether it's location or not
             */
            if (update.getMessage().getLocation() == null) {

                message.setText("Please send your location");
            } else {
                message.setText(sendWeather(update));
            }

            message.setChatId(update.getMessage().getChatId());

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        });

        tread.start();


    }

    @Override
    public String getBotUsername() {
        return Keys.Bot_name;
    }

    @Override
    public String getBotToken() {
        return Keys.Telegram_Key;
    }

    /**
     * Sends request to weather API.
     * Returns: string with weather info
     */
    private String sendWeather(Update update) {

        System.out.println(update.getMessage().getLocation());

        Weather weather = new Weather();

        String forecast = "The weather is ";

        try {

            weather.getRequestFromAPI(update.getMessage().getLocation().getLatitude(), update.getMessage().getLocation().getLongitude());


            forecast = forecast + Double.toString(weather.getTemp()) + "C, wind speed is " + String.valueOf(weather.getWind()); // " and it feels like: " + feels.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return forecast;
    }
}
