package com.example.flightapplication;

public class Route {
    private String from;
    private String to;
    private String date;

    public Route(String from, String to, String date) {
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public Route() {

    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Route{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
