package com.example.rrmp.ramp_init;

import java.util.List;

public class Professor {
    private int id;
    private String name;
    private List<String> courses;
    private String rating;

    public Professor() {
    }
    public Professor(int id, String name, List<String> courses, String rating) {
        this.id = id;
        this.name = name;
        this.courses = courses;
        this.rating = rating;
    }

    public Professor(int id, String name, List<String> courses, double numericRating) {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getCourses() {
        return courses;
    }

    public String getRating() {
        return rating;
    }

    public double getNumericRating() {
        if (rating != null && rating.contains("/")) {
            try {
                return Double.parseDouble(rating.split("/")[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0.0;
    }
}
