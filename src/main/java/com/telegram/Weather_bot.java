package com.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Weather_bot extends TelegramLongPollingBot {

    /**
     * This method runs when you receive message from Telegram.
     */
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());

        SendMessage message = new SendMessage();

        message.setText("Hello");

        message.setChatId(update.getMessage().getChatId());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
