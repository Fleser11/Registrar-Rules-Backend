package org.formalmethods.registrarproject.api.impl;

import org.openapitools.model.Course;
import org.springframework.stereotype.Component;
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

    private static Course parseToCourse(DBCourseDocument courseDoc) {
        Course course = new Course();
        course.setCode(courseDoc.getCode());
        course.setDept(courseDoc.getDept());
        course.setNumber(Integer.parseInt(courseDoc.getNumber()));
        course.setName(courseDoc.getName());
        course.setCredits(Float.parseFloat(courseDoc.getCredits()));
        course.setSemester(courseDoc.getSemester());
        course.setPrereqs(courseDoc.getPrereqs());
        return course;
    }


}