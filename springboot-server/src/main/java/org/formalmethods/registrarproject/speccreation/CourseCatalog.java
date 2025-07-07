package org.formalmethods.registrarproject.speccreation;

import java.util.List;

import org.formalmethods.registrarproject.db.DBService;
import org.formalmethods.registrarproject.db.dbtypes.DBCourseDocument;


import org.springframework.stereotype.Component;

@Component
class CourseCatalog extends CourseCollection {
    //AUTO Builds a CourseCollection instance of the CourseCatalog

    public CourseCatalog(DBService db) {
        super();
        loadCourses(db);
    }

    private void loadCourses(DBService db){
        List<DBCourseDocument> courses = db.getAllCourses();
        if (courses == null || courses.isEmpty()) {
            throw new IllegalStateException("No courses found in the database.");
        }

        courses.forEach(course -> {
            String code = course.getCode();
            String dept = course.getDept();
            int number;
            try {
                // Ensure number is parsed correctly
                number = Integer.parseInt(course.getNumber());
            } catch (NumberFormatException e) {
                number = 9999; // Default to 9999 if parsing fails
            }
            String name = course.getName();
            float credits;
            try {//TODO: go back and fix typing across service for credits
                credits = Float.parseFloat(course.getCredits());
            } catch (NumberFormatException e) {
                credits = 0.0f;
            }
            String semester = course.getSemester();
            String prereqs = course.getPrereqs();

            addCourse(
                code,
                dept,
                number,
                name,
                credits,
                semester,
                prereqs
            );
        });
    }
}