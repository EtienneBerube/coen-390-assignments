package com.example.assignment_1.models;

import java.util.HashMap;

public class Course {
    private String name;
    private HashMap<String, Double> grades = new HashMap<>();


    public Course(String name) {

        this.name = name;

        //Creates between 0 and 7 assignments for this course

        int numberOfAssignments = (int) (Math.random() * 7) + 0;

        for (int i = 1; i < numberOfAssignments; i++) {
            grades.put("Assignment " + i, Math.floor(Math.random() * 56) + 45); // get grade between 45 and 100
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sends a Map of the assignment name and the grade as a number
     * @return
     */
    public HashMap<String, Double> getGrades() {
        return grades;
    }

    public void setGrades(HashMap<String, Double> grades) {
        this.grades = grades;
    }


    /**
     * Sends a Map of the assignment name and the grade as a letter
     * @return
     */
    public HashMap<String, String> getGradesAsLetters() {
        HashMap<String, String> gradesAsString = new HashMap<>();
        grades.entrySet().forEach((entry) -> gradesAsString.put(entry.getKey(), getLetterGrade(entry.getValue())));
        return gradesAsString;
    }

    /**
     * Returns the letter equivalent to a number
     * @param grade the grade as a Double
     * @return
     */
    private String getLetterGrade(Double grade) {
        if (grade >= 90) {
            return "A+";
        } else if (grade >= 85) {
            return "A";
        } else if (grade >= 80) {
            return "A-";
        } else if (grade >= 77) {
            return "B+";
        } else if (grade >= 73) {
            return "B";
        } else if (grade >= 70) {
            return "B-";
        } else if (grade >= 67) {
            return "C+";
        } else if (grade >= 63) {
            return "C";
        } else if (grade >= 60) {
            return "C-";
        } else if (grade >= 57) {
            return "D+";
        } else if (grade >= 53) {
            return "D";
        } else if (grade >= 50) {
            return "D-";
        } else {
            return "F";
        }
    }

    public Double getAverage(){
        return grades.entrySet().stream().mapToDouble(entry -> entry.getValue()).sum() / grades.size();
    }

    public String getAverageLetter(){
        return getLetterGrade(getAverage());
    }
}
