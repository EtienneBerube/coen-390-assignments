package com.example.assignment_1.helpers;

import com.example.assignment_1.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseStore {
    public static CourseStore instance;
    public List<Course> courses = new ArrayList<>();

    public static CourseStore getInstance() {
        if(instance != null) {
            return instance;
        }else{
            return new CourseStore();
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
