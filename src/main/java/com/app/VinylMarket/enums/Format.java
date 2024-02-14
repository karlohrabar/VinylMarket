package com.app.VinylMarket.enums;

public enum Format {
    LP("LP"),
    EP("Extended play"),
    SINGLE("Single"),

    MAXI_SINGLE("Maxi single");

    private final String displayValue;

    Format(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }

}
