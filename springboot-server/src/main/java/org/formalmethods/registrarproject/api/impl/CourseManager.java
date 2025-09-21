package org.formalmethods.registrarproject.api.impl;

import org.openapitools.model.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.formalmethods.registrarproject.db.DBService;
import org.formalmethods.registrarproject.db.dbtypes.DBCourseDocument;

import org.formalmethods.registrarproject.exception.MissingItemException;

@Component
public class CourseManager{

    private DBService db;

    public CourseManager(DBService dbService) {
        this.db = dbService;
    }

    public Course getCourse(String code) {
        DBCourseDocument courseDoc = db.getCourse(code);
        if (courseDoc == null) {
            throw new MissingItemException("Course with code " + code + " not found.");
        }
        return parseToCourse(courseDoc);
    }

    public List<Course> getAllCourses(){

        ArrayList<Course> courses = new ArrayList<Course>();
        for (DBCourseDocument courseDoc : db.getAllCourses()) 
            courses.add(parseToCourse(courseDoc));
        return courses;
    }

    private static Course parseToCourse(DBCourseDocument courseDoc) {
        Course course = new Course();
        course.setCode(courseDoc.getCode());
        course.setDept(courseDoc.getDept());

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(courseDoc.getNumber());
        if (matcher.find()) {
            course.setNumber(Integer.parseInt(matcher.group()));
        } else {
            course.setNumber(9999); // Default to 9999 if parsing fails
        }

        course.setName(courseDoc.getName());
        try {
            course.setCredits(Float.parseFloat(courseDoc.getCredits()));
        } catch (NumberFormatException e) {
            course.setCredits(0.0f);
        }
        course.setSemester(courseDoc.getSemester());
        course.setPrereqs(courseDoc.getPrereqs());
        course.setDescription(courseDoc.getDescription());
        return course;
    }


}