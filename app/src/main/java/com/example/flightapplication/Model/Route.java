package com.example.flightapplication.Model;

public class Route {
    private String from;
    private String to;
    private String flightNumber;
    private String price;

    public Route(String from, String to, String flightNumber,String price) {
        this.from = from;
        this.to = to;
        this.flightNumber = flightNumber;
        this.price = price;

    }

    public Route() {

    }



    public void setFrom(String from) {
        this.from = from;
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

    public String getFlightNumber( ) {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


    @Override
    public String toString() {
        return "Route{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
               // ", date='"  + '\'' +
                '}';
    }
}
