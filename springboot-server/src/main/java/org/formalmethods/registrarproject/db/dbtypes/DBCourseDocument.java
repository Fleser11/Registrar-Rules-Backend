package org.formalmethods.registrarproject.db.dbtypes;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class DBCourseDocument {
    private String code;
    private String dept;
    private int number;
    private String name;
    private float credits;
    private String semester;
    private String prereqs;

    public DBCourseDocument(String code, String dept, int number, String name, float credits, String semester, String prereqs) {
        this.code = code;
        this.dept = dept;
        this.number = number;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.prereqs = prereqs;
    }
    public String getCode() {
        return code;
    }
    public String getDept() {
        return dept;
    }
    public int getNumber() {
        return number;
    }
    public String getName() {
        return name;
    }
    public float getCredits() {
        return credits;
    }
    public String getSemester() {
        return semester;
    }
    public String getPrereqs() {
        return prereqs;
    }

}
