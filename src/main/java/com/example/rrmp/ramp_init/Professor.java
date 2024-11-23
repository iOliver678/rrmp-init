package com.example.rrmp.ramp_init;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Professor {
    @JsonProperty("tid")
    private int tid;

    @JsonProperty("tFname")
    private String tFname;

    @JsonProperty("tLname")
    private String tLname;

    @JsonProperty("tNumRatings")
    private int tNumRatings;

    @JsonProperty("rating_class")
    private String rating_class;

    @JsonProperty("overall_rating")
    private String overall_rating;

    @JsonProperty("courseCodes")
    private List<CourseCode> courseCodes;

    // Getters
    public int getTid() {
        return tid;
    }

    public String getTFname() {
        return tFname;
    }

    public String getTLname() {
        return tLname;
    }

    public int getTNumRatings() {
        return tNumRatings;
    }

    public String getRating_class() {
        return rating_class;
    }

    public String getOverall_rating() {
        return overall_rating;
    }

    public List<String> getCourses() {
        List<String> courses = new ArrayList<>();
        if (courseCodes != null) {
            for (CourseCode code : courseCodes) {
                if (code.getCourseName() != null) {
                    courses.add(code.getCourseName());
                }
            }
        }
        return courses;
    }

    public double getNumericRating() {
        if (overall_rating != null && !overall_rating.equals("N/A")) {
            try {
                return Double.parseDouble(overall_rating);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return 0.0;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CourseCode {
        private String courseName;
        private int courseCount;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }
}
