package com.example.assignment_1.helpers;

import com.example.assignment_1.models.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * A Container for the courses. Therefore, if the desire to make the classes immutable during the
 * duration of the app, it would be doable with a Singleton.
 */
public class CourseStore {

    public List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }
}
