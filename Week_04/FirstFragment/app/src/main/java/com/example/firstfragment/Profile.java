package com.example.firstfragment;


import java.io.Serializable;

public class Profile implements Serializable {
    String username;
    double age;
    String education;

    public Profile() {
    }

    public Profile(String username, double age) {
        this.username = username;
        this.age = age;

    }

    public Profile(String username, double age, String education) {
        this.username = username;
        this.age = age;
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
