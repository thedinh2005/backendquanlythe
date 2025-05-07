package com.example.quanlythe.model;

public class Card {
    private Long ID;
    private String card_number;
    private String card_holder;
    private String card_type;
    private String opened_date;
    private String cvv;

    // Constructors
    public Card(String card_number, String card_holder, String card_type, String opened_date,String cvv) {
        this.card_number = card_number;
        this.card_holder = card_holder;
        this.card_type = card_type;
        this.opened_date = opened_date;
        this.cvv=cvv;
    }

    public Card() {}


    // Getters and Setters
    public Long getID() {
        return ID;
    }

    public String getCard_number() {
        return card_number;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public String getCard_type() {
        return card_type;
    }

    public String getOpened_date() {
        return opened_date;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public void setOpened_date(String opened_date) {
        this.opened_date = opened_date;
    }
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
