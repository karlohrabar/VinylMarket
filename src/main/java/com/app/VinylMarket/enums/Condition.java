package com.app.VinylMarket.enums;

public enum Condition {
    MINT("Mint"),
    NM("Near mint"),

    VGPLUS("Very good +"),

    VG("Very good"),

    GOODPLUS("Good +"),

    GOOD("Good"),

    FAIR("Fair");

    private final String value;

    private Condition(String value){
        this.value = value;
    }

    private String getValue(){
        return value;
    }
}
