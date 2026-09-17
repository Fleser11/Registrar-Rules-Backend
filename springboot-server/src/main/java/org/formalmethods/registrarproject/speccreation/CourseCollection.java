package org.formalmethods.registrarproject.speccreation;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.Map;
import java.util.HashMap;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.Variable;
import com.bpodgursky.jbool_expressions.Or;
import com.bpodgursky.jbool_expressions.And;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

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

        private static class CourseRequirements{

            String prereqsNames[][] = {
                {"Or1", "And2", "Or3"},{"And1", "Or2", "And3"}
            };

            String baseCourse;
            int identifier;
            int depth;
            int isOr;
            List<String> courseReqs;

            public CourseRequirements(String baseCourse, List<String> courseReqs, int[] identifier, int depth, int isOr){
                this.baseCourse = baseCourse;
                this.identifier = identifier[0];
                this.depth = depth;
                this.isOr = isOr;
                this.courseReqs = courseReqs;

            }
            
            public String getCourseAlloy(){
                StringBuilder sb = new StringBuilder(
                    "one sig "+ getName() + " extends " + prereqsNames[isOr][depth] + "{}{\n"
                );

                sb.append("sub" + prereqsNames[isOr][depth] + " = " + String.join(" + ", courseReqs));
                sb.append("\n}\n");
                

                return sb.toString();
            }

            public String getName(){
                return baseCourse + "_REQ_" + identifier + prereqsNames[isOr][depth]; 
            }
        }

        private String getSubReq(Expression<String> expr,
            String baseCourse,
            int[] identifier,
            int depth,
            int isOr,
            StringBuilder sb
        ){
            if (expr instanceof Variable){
                return ((Variable<String>)expr).getValue();
            }
            else{
                List<String> subReqs = new LinkedList<String>();
                for (Expression<String> child: expr.getChildren()){
                    String cr = getSubReq(
                        child,
                        baseCourse,
                        identifier,
                        depth + 1,
                        isOr,
                        sb
                    );
                    if (cr.equals("ALEKS") || cr.equals("CEEB")){
                        continue;
                    }
                    else{
                        subReqs.add(cr);
                        identifier[0]++;
                    }
                }
            
                CourseRequirements cr = new CourseRequirements(
                    baseCourse,
                    subReqs,
                    identifier,
                    depth,
                    isOr
                    );
                sb.append(cr.getCourseAlloy());
                return cr.getName();
            }

        }

        public String getCourseAlloy(){
            String header = "one sig " + dept + num + " extends Course {}{\n";
            String body = "";

            String courseName = dept+num;

            StringBuilder sb = new StringBuilder("");

            //Parse the prereqs into a boolean expression and map them back to requirements
            if (!prereqs.equals("") && !prereqs.trim().contains("ALEKS") && !prereqs.trim().contains("CEEB")) {
                int identifierCount = 0;
                int depth = 0;
                int isOr = 1;

                String compatiblePrereqs = prereqs.replace("or", "|").replace("and", "&").replace("(C)", "");
                System.out.println("Prerequisite for " + courseName + " " + prereqs);
                Expression<String> expr = ExprParser.parse(compatiblePrereqs);
                if (expr.getClass() == Or.class){
                    isOr = 0;
                }
                else if (expr.getClass() == Variable.class){
                    expr = And.of(expr);
                }

                int[] identifier = {0};
                String req = (String)getSubReq(expr, courseName, identifier, depth, isOr, sb);
                body = "req = " + req + "\n}\n";
            } else {
                System.out.println("Prerequisite skipped is for: " + courseName + " " + prereqs);
                body = "}\n";

            }
            return sb.toString() + header + body;
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
