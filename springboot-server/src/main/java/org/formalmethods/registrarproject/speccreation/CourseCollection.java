package org.formalmethods.registrarproject.speccreation;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.Map;
import java.util.HashMap;

class CourseCollection {

    private Map<String, Course> courseList = new HashMap<>();

    public static class Course{

        private String code;
        private String dept;
        private int num;
        private String name;
        private float credits;
        private String sem;
        private String prereqs;

        public Course(String code, String dept, int num, String name, float credits, String sem, String prereqs){
            this.code = code;
            this.dept = dept;
            this.num = num;
            this.name = name;
            this.credits = credits;
            this.sem = sem;
            this.prereqs = prereqs;
        }

        public String getDept() {
            return dept;
        }

        public int getNum() {
            return num;
        }

        public String getName() {
            return name;
        }

        public float getCredits() {
            return credits;
        }

        public String getSem() {
            return sem;
        }

        public String getPrereqs() {
            return prereqs;
        }

        public String getCourseAlloy(){
            String header = "one sig " + dept + num + " extends Course {}{\n";
            String body = "";

            if (!prereqs.contains("ALEKS") && !prereqs.contains("CEEB")){

                Pattern preReqPattern = Pattern.compile("([A-Za-z][A-Za-z]+[0-9]{4})(\\(C\\))?");
                body = preReqPattern.matcher(prereqs).replaceAll("$1 in prereqs") + "\n}\n";
            } else {
                body = "}\n";
            }
            return header + body;
        }
    }

    public Map<String, Course> getCourseList(){
        return courseList;
    }

    public Course getCourse(String code) {
        return courseList.get(code);
    }

    public Course addCourse(String code, String dept, int num, String name, float credits, String sem, String prereqs) {
        Course course = new Course(code, dept, num, name, credits, sem, prereqs);
        courseList.put(code, course);
        return course;
    }

    public Course addCourse(Course course) {
        if (courseList.containsKey(course.code)) {
            throw new IllegalArgumentException("Course with code " + course.code + " already exists.");
        }
        courseList.put(course.code, course);
        return course;
    }

    public static CourseCollection combine(CourseCollection a, CourseCollection b) {
        CourseCollection combined = new CourseCollection();
        combined.courseList.putAll(a.courseList);
        combined.courseList.putAll(b.courseList);
        return combined;
    }
}
