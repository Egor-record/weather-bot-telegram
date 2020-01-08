package com.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;


public class Main {

        public static void main(String[] args) throws Exception {

          //  Weather weather = new Weather();

           // weather.getRequestFromAPI();

            TelegramBot bot = new TelegramBot(Keys.Telegram_Key);

            GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

            GetUpdatesResponse updatesResponse = bot.execute(getUpdates);

            System.out.println(updatesResponse);


        }
}
