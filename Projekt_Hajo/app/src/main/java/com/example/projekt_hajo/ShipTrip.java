package com.example.projekt_hajo;

public class ShipTrip {
    private String nev;
    private String info;
    private String ar;
    private final int imageRes;

    public ShipTrip(String nev, String info, String ar, int imageRes) {
        this.nev = nev;
        this.info = info;
        this.ar = ar;
        this.imageRes = imageRes;
    }

    public String getNev() {
        return nev;
    }

    public String getInfo() {
        return info;
    }

    public String getAr() {
        return ar;
    }

    public int getImageRes() {
        return imageRes;
    }
}
