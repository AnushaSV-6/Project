package com.nits.service;

import com.nits.dao.CourseDAO;
import com.nits.model.Course;
import com.nits.model.UserCourse;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    public List<Course> getAllCourses() throws Exception {
        return courseDAO.getAllCourses();
    }
    public boolean addCourse(String code, String name, String description) throws Exception {
        return courseDAO.addCourse(code, name, description);
    
    }
    public Course getCourseByCode(String code) throws Exception {
        return courseDAO.getCourseByCode(code);
    }
    public boolean deleteCourse(String code) throws Exception {
        return courseDAO.deleteCourse(code);
    }
    public List<UserCourse> getAllUserCourses() throws Exception {
        return CourseDAO.getAllUserCourses();
    }
    public boolean updateCourse(String code, String name, String description) throws Exception {
        CourseDAO courseDAO = new CourseDAO();
        return courseDAO.updateCourse(code, name, description);
    }

}
