package com.example.assessment2;

import java.io.Serializable;

public class Profile implements Serializable {
    String weight;
    String gender;

    public Profile(String weight, String gender) {
        this.weight = weight;
        this.gender = gender;
    }

    public Profile() {
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
