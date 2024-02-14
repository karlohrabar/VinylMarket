package com.app.VinylMarket.enums;

public enum Genre {

    CLASSICAL("Classical"),
    COUNTRY("Country"),
    ELECTRONIC("Electronic"),
    FOLK("Folk"),
    HIPHOP("Hip hop"),
    INDIE("Indie"),
    JAZZ("Jazz"),
    METAL("Metal"),
    POP("Pop"),
    ROCK("Rock"),
    WORLD("World");

    private final String displayValue;

    Genre(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }

}
