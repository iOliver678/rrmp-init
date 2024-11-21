package com.example.rrmp.ramp_init;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during deserialization
public class ProfessorJsonRecord {
    @JsonProperty("tDept")
    private String tDept;

    @JsonProperty("tFname")
    private String tFname;

    @JsonProperty("tLname")
    private String tLname;

    @JsonProperty("overallRating")
    private String overallRating;

    @JsonProperty("courseCodes")
    private List<CourseCode> courseCodes;

    public String gettDept() {
        return tDept;
    }

    public void settDept(String tDept) {
        this.tDept = tDept;
    }

    public String gettFname() {
        return tFname;
    }

    public void settFname(String tFname) {
        this.tFname = tFname;
    }

    public String gettLname() {
        return tLname;
    }

    public void settLname(String tLname) {
        this.tLname = tLname;
    }

    public String getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(String overallRating) {
        this.overallRating = overallRating;
    }

    public List<CourseCode> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(List<CourseCode> courseCodes) {
        this.courseCodes = courseCodes;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CourseCode {
        private String courseName;

        public CourseCode() {
        }

        public CourseCode(String courseName) {
            this.courseName = "N/A".equalsIgnoreCase(courseName) ? null : courseName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }
    }
}
