package com.overseascab.overseascab.Models;

public class Vehicles {

    String car_name;
    String car_image;
    String distance;
    String time;
    String t_fare;
    String b_fare;

    public Vehicles(String car_name, String car_image, String distance, String time, String t_fare, String b_fare) {
        this.car_name = car_name;
        this.distance = distance;
        this.time = time;
        this.t_fare = t_fare;
        this.b_fare = b_fare;
        this.car_image = car_image;
    }

    public String getCar_name() {
        return car_name;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }

    public String getT_fare() {
        return t_fare;
    }

    public String getB_fare() {
        return b_fare;
    }

    public String getCar_image() {
        return car_image;
    }
}
