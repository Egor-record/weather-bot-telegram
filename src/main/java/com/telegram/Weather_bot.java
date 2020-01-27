package com.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.telegram.Users.SubscriberBuilder;

import java.util.List;

public class Weather_bot extends TelegramLongPollingBot {

    /**
     * This method runs when you receive message from Telegram. Each request from Telegram runs in separate thead
     */
    @Override
    public void onUpdateReceived(Update update) {

        Thread tread = new Thread(() -> {

            System.out.println(update.getMessage().getText());

            /*
             *  Check the message from Telegram
             */
            if (update.getMessage().getLocation() != null) {

                /*
                 *  Location - OK
                 */
                sendMessage(update, sendWeather(update.getMessage().getLocation().getLatitude(), update.getMessage().getLocation().getLongitude()));

                if (isTheNewUser(update)) {
                    createNewUser(update);
                    askUserAboutSubscription(update);
                } else {
                    if (!checkIfUserHasSubscription(update)) {
                        askUserAboutSubscription(update);
                    }
                }

            } else if (update.getMessage().getText().equals("/subscribe")) {
                /*
                 *  Subscribe - OK
                 */

                if (isTheNewUser(update)) {
                    sendMessage(update,"Please, send us your location first");
                } else {
                    if (!checkIfUserHasSubscription(update)) {

                       Users user = Users.findUserByChatID(update.getMessage().getChatId()).get(0);
                       user.setSubscribed(true);
                       Users.update(user);

                        sendMessage(update,"Great! You'll now receive daily weather forecast. To unsubscribe, please, send us /unsubscribe command.");
                    } else {
                        sendMessage(update,"Seems like you already subscribed");
                    }

                }

            } else if  (update.getMessage().getText().equals("/unsubscribe")) {
                /*
                * Cancel subscriber - OK
                */

                if (isTheNewUser(update) || !checkIfUserHasSubscription(update)) {
                    sendMessage(update,"Seems you don't have subscription");
                } else {
                    Users user = Users.findUserByChatID(update.getMessage().getChatId()).get(0);
                    user.setSubscribed(false);
                    Users.update(user);
                    sendMessage(update,"Subscription canceled!");
                }


            } else {
                /*
                 * The rest of messages we don't handle
                 */
                sendMessage(update, "Please send your location");
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
    private String sendWeather(Float lat, Float lon) {

        Weather weather = new Weather();

        String forecast = "";

        try {

            weather.getRequestFromAPI(lat, lon);

            forecast = "The weather is " + weather.getTemp() + "C";


        } catch (Exception e) {
            forecast = "Seems like we have a problem. We are now working on it.";
            e.printStackTrace();
        }


        return forecast;
    }

    private void sendForecastToSubscribers() {
        Users.fetchAllSubscribedUsers().forEach(System.out::println);
    }

    /**
     * This method creates new user.
     * We store her chat id, whether she has subscription or not and her Latitude with Longitude
     * @param update
     */
    private void createNewUser(Update update) {

        Users users = new SubscriberBuilder()
                .withChatID(update.getMessage().getChatId())
                .withSubscription(false)
                .withLatitude(update.getMessage().getLocation().getLatitude())
                .withLongitude(update.getMessage().getLocation().getLongitude())
                .build();

        users.saveUser(users);
    }

    /**
     * This method sends to user the message asking if she wants so subscribe to daily weather forecast
     * @param update
     */
    private void askUserAboutSubscription(Update update) {
        sendMessage(update,"Would you also like to receive this forecast daily with this location? Please send /subscribe for this");
    }

    private boolean isTheNewUser(Update update){
        System.out.println(Users.findUserByChatID(update.getMessage().getChatId()).size());
        return Users.findUserByChatID(update.getMessage().getChatId()).size() == 0;
    }

    /**
     * Method checks if user has subscription
     * @param update - message from Telegram
     * @return true if subscription is active and false if not
     */
    private boolean checkIfUserHasSubscription(Update update) {
        return Users.findUserByChatID(update.getMessage().getChatId()).get(0).getSubscribed();
    }

    /**
     * Method which sends to user message
     * @param update - response from Telegram
     * @param text text you wanted to send in response to user
     */
    private void sendMessage(Update update, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
