package com.example.flightapplication.Model;

public class Route {
    private String routeId;
    private String from;
    private String to;
    private String price;
    private String date;
    private String time;

    public Route( ) {
    }

    public Route(String routeId, String from, String to, String price, String date,String time) {
        this.routeId = routeId;
        this.from = from;
        this.to = to;
        this.price = price;
        this.date = date;
        this.time = time;
    }

    public String getTime( ) {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRouteId( ) {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getFrom( ) {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo( ) {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public String getPrice( ) {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate( ) {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
