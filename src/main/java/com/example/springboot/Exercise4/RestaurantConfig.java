package com.example.springboot.Exercise4;

import org.springframework.stereotype.Component;

@Component
public class RestaurantConfig {
    private double minPrice = 5.5;
    private double todaysDiscount = 1.5;
    private double maxPrice = 20.0;
    public RestaurantConfig(){
    }
    public double getMinPrice() {return minPrice;}
    public void setMinPrice(double minPrice) {this.minPrice = minPrice;}
    public double getTodaysDiscount() {return todaysDiscount;}
    public void setTodaysDiscount(double todaysDiscount) {this.todaysDiscount = todaysDiscount;}
    public double getMaxPrice() {return maxPrice;}
    public void setMaxPrice(double maxPrice) {this.maxPrice = maxPrice;}
}