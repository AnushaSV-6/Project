package com.nits.model;

public  class UserCourse {
    private String email;
    private String code;
    private String courseName;

    public UserCourse(String email, String courseCode, String courseName) {
        this.email = email;
        this.code = courseCode;
        this.courseName = courseName;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public String getCourseName() {
        return courseName;
    }
}
