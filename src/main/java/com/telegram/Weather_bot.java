package com.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Weather_bot extends TelegramLongPollingBot {

    /**
     * This method runs when you receive message from Telegram.
     */
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }

    @Override
    public String getBotUsername() {
        return Keys.Bot_name;
    }

    @Override
    public String getBotToken() {
        return Keys.Telegram_Key;
    }
}
