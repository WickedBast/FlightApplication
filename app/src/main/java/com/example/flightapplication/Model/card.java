package com.example.flightapplication.Model;

public class card {
    private String bakiye;

    public card(String bakiye){
        this.bakiye = bakiye;
    }
    public card(){}

    public String getBakiye() {
        return bakiye;
    }

    public void setBakiye(String bakiye) {
        this.bakiye = bakiye;
    }

    @Override
    public String toString() {
        return "card{" +
                "bakiye='" + bakiye + '\'' +
                '}';
    }
}

