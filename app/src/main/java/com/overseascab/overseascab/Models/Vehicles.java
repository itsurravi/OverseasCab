package com.overseascab.overseascab.Models;

public class Vehicles {

    String id;
    String carname;
    String carimage;
    String basedistance;
    String baseprice;
    String unitdistance;
    String seatingcap;
    String booked;

    public Vehicles(String id, String carname, String carimage, String basedistance, String baseprice, String unitdistance, String seatingcap, String booked) {
        this.id = id;
        this.carname = carname;
        this.carimage = carimage;
        this.basedistance = basedistance;
        this.baseprice = baseprice;
        this.unitdistance = unitdistance;
        this.seatingcap = seatingcap;
        this.booked = booked;
    }

    public String getId() {
        return id;
    }

    public String getCarname() {
        return carname;
    }

    public String getCarimage() {
        return carimage;
    }

    public String getBasedistance() {
        return basedistance;
    }

    public String getBaseprice() {
        return baseprice;
    }

    public String getUnitdistance() {
        return unitdistance;
    }

    public String getSeatingcap() {
        return seatingcap;
    }

    public String getBooked() {
        return booked;
    }
}
