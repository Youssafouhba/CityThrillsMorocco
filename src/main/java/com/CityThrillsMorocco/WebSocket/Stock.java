package com.CityThrillsMorocco.WebSocket;

import lombok.Data;

@Data
public class Stock {
    String name;
    String icon;
    float price;
    boolean increased;

    public Stock(String name, String icon, float price) {
        this.name = name;
        this.icon = icon;
        this.price = price;
    }

    // Getters & setters ...
}