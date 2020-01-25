package com.telegram;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private int id;

    @Column
    private String ChatID;

    @Column
    private Boolean IsSubscribed;

    public Subscribe() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChatID() {
        return ChatID;
    }

    public void setChatID(String chatID) {
        ChatID = chatID;
    }

    public Boolean getSubscribed() {
        return IsSubscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        IsSubscribed = subscribed;
    }
}
