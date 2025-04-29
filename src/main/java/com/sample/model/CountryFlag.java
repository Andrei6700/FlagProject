package com.sample.model;

import java.util.ArrayList;
import java.util.List;

public class CountryFlag {
    private String country;
    private List<String> colors = new ArrayList<>();

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public void addColor(String color) {
        this.colors.add(color);
    }
}