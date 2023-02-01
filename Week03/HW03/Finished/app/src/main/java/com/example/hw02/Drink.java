package com.example.hw02;

import java.io.Serializable;

public class Drink implements Serializable {
    int drinkSize;
    double alcoholPercentage;

    public Drink() {
    }

    public Drink(int drinkSize, double alcoholPercentage) {
        this.drinkSize = drinkSize;
        this.alcoholPercentage = alcoholPercentage;
    }

    public int getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(int drinkSize) {
        this.drinkSize = drinkSize;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }
}