package org.formalmethods.registrarproject.speccreation;

import java.util.regex.*;
import java.util.List;
import java.util.ArrayList;

import org.formalmethods.registrarproject.db.DBService;
import org.formalmethods.registrarproject.speccreation.CourseCollection;
import org.formalmethods.registrarproject.speccreation.CourseCollection.Course;
import org.formalmethods.registrarproject.speccreation.CourseCatalog;

import org.openapitools.model.Audit;
import org.openapitools.model.SubAudit;

class CourseSpecGenerator {

    private String COURSE_HEADER = """
        abstract sig Course {
            prereqs: set Course
        }

        var sig semCourses in Course{}{
        #semCourses <= 6
        }

        var sig takenCourses in semCourses{}
        var sig passedCourses in takenCourses{}

        sig Course1000 in Course{}
        sig Course2000 in Course{}
        sig Course3000 in Course{}
        sig Course4000 in Course{}
        fact {
            disj[Course1000, Course2000, Course3000, Course4000]
            and (all c: semCourses | c in (Course1000 + Course2000 + Course3000 + Course4000))
        }
        """;


    private CourseCollection baseCourseCollection;
    private CourseCollection courseCollection;
    private List<String> abstractCourseList;

    CourseSpecGenerator(Audit primaryAudit, Audit genEdAudit, DBService db){
        baseCourseCollection = new CourseCatalog(db);
        courseCollection = createCourseCollection(primaryAudit, genEdAudit);
        abstractCourseList = getAbstractCourseList(primaryAudit, genEdAudit);
    }

    private void loadPrereqsRecursive(CourseCollection baseCollection, CourseCollection courseCollection, String course){
        if (courseCollection.getCourse(course) == null) {
            if (baseCollection.getCourse(course) == null) {
                System.err.println("Course " + course + " not found in either collection.");
                Pattern coursePattern = Pattern.compile("([a-zA-Z][a-zA-Z]+)([0-9][0-9][0-9][0-9])");
                Matcher matcher = coursePattern.matcher(course);
                if (matcher.matches()) {
                    String dept = matcher.group(1);
                    String num = matcher.group(2);
                    System.err.println("Adding course " + dept + num + " to collection.");
                    baseCollection.addCourse(dept + num, dept, Integer.parseInt(num), "Unknown Course", 0.0f, "Unknown Semester", "");
                } else {
                    throw new IllegalArgumentException("Invalid course format: " + course);
                }
            }
            courseCollection.addCourse(baseCollection.getCourse(course));
        }

        Course currentCourse = courseCollection.getCourse(course);
        Pattern coursePattern = Pattern.compile("([a-zA-Z][a-zA-Z]+)([0-9][0-9][0-9][0-9])");
        Matcher matcher = coursePattern.matcher(currentCourse.getPrereqs());
        while( matcher.find()) {
            String prereq = matcher.group(1) + matcher.group(2);
            if (courseCollection.getCourse(prereq) == null) {
                loadPrereqsRecursive(baseCollection, courseCollection, prereq);
            }
        }
    }

    //TODO: Fix naming scheme for subAudit
    private CourseCollection createCourseCollection(Audit primaryAudit, Audit genEdAudit) {
        Pattern nonAbstractCoursePattern = Pattern.compile("(?<!abstract_)([a-zA-Z][a-zA-Z]+[0-9][0-9][0-9][0-9])");
        CourseCollection collection = new CourseCollection();
        for (SubAudit subAudit : primaryAudit.getSubAudit()) {
            for (String course: subAudit.getCourses()) {
                Matcher matcher = nonAbstractCoursePattern.matcher(course);
                while(matcher.find()){
                    loadPrereqsRecursive(baseCourseCollection, collection, matcher.group(1));
                }
            }
        }
        for (SubAudit subAudit : genEdAudit.getSubAudit()) {
            for (String course: subAudit.getCourses()) {
                Matcher matcher = nonAbstractCoursePattern.matcher(course);
                while(matcher.find()){
                    loadPrereqsRecursive(baseCourseCollection, collection, matcher.group(1));
                }
            }
        }
        return collection;
    }

    private List<String> getAbstractCourseList(Audit primaryAudit, Audit genEdAudit) {
        List<String> abstractCourses = new ArrayList<>();
        Pattern abstractCoursePattern = Pattern.compile("(abstract_[a-zA-Z0-9]*)");
        for (SubAudit subAudit : primaryAudit.getSubAudit()) {
            for (String course : subAudit.getCourses()) {
                Matcher matcher = abstractCoursePattern.matcher(course);
                while (matcher.find()) {
                    abstractCourses.add(matcher.group(1));
                }
            }
        }
        for (SubAudit subAudit : genEdAudit.getSubAudit()) {
            for (String course : subAudit.getCourses()) {
                Matcher matcher = abstractCoursePattern.matcher(course);
                while (matcher.find()) {
                    abstractCourses.add(matcher.group(1));
                }
            }
        }
        return abstractCourses;
    }

    private String getCoursesAlloyString(CourseCollection collection) {
        StringBuilder alloyStringBuilder = new StringBuilder();
        collection.getCourseList().values().forEach(course -> {
            alloyStringBuilder.append(course.getCourseAlloy());
        });
        return alloyStringBuilder.toString();
    }

    private String getAbstractCoursesAlloyString(List<String> abstractCourses) {
        StringBuilder alloyStringBuilder = new StringBuilder();
        abstractCourses.forEach(course -> {
            alloyStringBuilder.append("sig " + course + " extends Course{}\n");
        });
        return alloyStringBuilder.toString();
    }

    public String getCourseSpec(){
        StringBuilder courseSpecBuilder = new StringBuilder();
        courseSpecBuilder.append(COURSE_HEADER);
        courseSpecBuilder.append(getCoursesAlloyString(courseCollection));
        courseSpecBuilder.append(getAbstractCoursesAlloyString(abstractCourseList));
        return courseSpecBuilder.toString();
    }

}
