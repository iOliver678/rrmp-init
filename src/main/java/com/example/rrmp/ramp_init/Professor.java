package com.example.rrmp.ramp_init;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a professor entity with their associated details and ratings.
 * This class is designed to work with JSON deserialization using Jackson annotations.
 */
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

    /**
     * Gets the professor's ID.
     *
     * @return integer representing the professor's unique identifier
     */
    public int getTid() {
        return tid;
    }

    /**
     * Gets the professor's first name.
     *
     * @return String containing the professor's first name
     */
    public String getTFname() {
        return tFname;
    }

     /**
     * Gets the professor's last name.
     *
     * @return String containing the professor's last name
     */
    public String getTLname() {
        return tLname;
    }

    /**
     * Gets the total number of ratings for the professor.
     *
     * @return integer representing the number of ratings
     */
    public int getTNumRatings() {
        return tNumRatings;
    }

    /**
     * Gets the rating class category.
     *
     * @return String representing the rating classification
     */
    public String getRating_class() {
        return rating_class;
    }

    /**
     * Gets the professor's overall rating.
     *
     * @return String containing the overall rating value
     */
    public String getOverall_rating() {
        return overall_rating;
    }


     /**
     * Returns a list of course names taught by the professor.
     *
     * @return List<String> containing the names of courses
     */
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

    /**
     * Returns the professor's numeric rating.
     * Converts the string rating to a double value.
     *
     * @return double value of the rating, or 0.0 if rating is invalid or N/A
     */
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

    /**
    * Inner class representing a course code with associated metadata.
    */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CourseCode {
        private String courseName;
        private int courseCount;

        /**
         * Gets the name of the course.
         *
         * @return String containing the course name
         */
        public String getCourseName() {
            return courseName;
        }

        /**
         * Sets the name of the course.
         *
         * @param courseName The name to set for the course
         */
        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }
}
