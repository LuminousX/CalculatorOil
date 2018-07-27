package com.example.hispeed.calculatoroil.Adapter;

/**
 * Created by Hispeed on 20/8/2560.
 */

public class DetailRecyclerView {
    private String name;
    private String type_car;
    private String origin;
    private String destination;
    private String distance;
    private String duration;
    private String type_oil;
    private String spend_oil;
    private String money;
    private String str_date;
    private String average_baht;


    public DetailRecyclerView(String name, String type_car, String origin, String destination, String distance, String duration, String type_oil, String spend_oil, String money, String str_date, String average_baht) {
        this.name = name;
        this.type_car = type_car;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.type_oil = type_oil;
        this.spend_oil = spend_oil;
        this.money = money;
        this.str_date = str_date;
        this.average_baht = average_baht;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_car() {
        return type_car;
    }

    public void setType_car(String type_car) {
        this.type_car = type_car;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType_oil() {
        return type_oil;
    }

    public void setType_oil(String type_oil) {
        this.type_oil = type_oil;
    }

    public String getSpend_oil() {
        return spend_oil;
    }

    public void setSpend_oil(String spend_oil) {
        this.spend_oil = spend_oil;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStr_date() {
        return str_date;
    }

    public void setStr_date(String str_date) {
        this.str_date = str_date;
    }

    public String getAverage_baht() {
        return average_baht;
    }

    public void setAverage_baht(String average_baht) {
        this.average_baht = average_baht;
    }

}
