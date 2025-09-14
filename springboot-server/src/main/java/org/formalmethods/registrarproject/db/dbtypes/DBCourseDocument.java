package org.formalmethods.registrarproject.db.dbtypes;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class DBCourseDocument {
    private String code;
    private String dept;
    private String number;
    private String name;
    private String credits;
    private String semester;
    private String prereqs;
    private String description;

    public DBCourseDocument(
        String code, 
        String dept, 
        String number, 
        String name, 
        String credits, 
        String semester, 
        String prereqs,
        String description
    ) {
        this.code = code;
        this.dept = dept;
        this.number = number;
        this.name = name;
        this.credits = credits;
        this.semester = semester;
        this.prereqs = prereqs;
        this.description = description;
    }
    public String getCode() {
        return code;
    }
    public String getDept() {
        return dept;
    }
    public String getNumber() {
        return number;
    }
    public String getName() {
        return name;
    }
    public String getCredits() {
        return credits;
    }
    public String getSemester() {
        return semester;
    }
    public String getPrereqs() {
        return prereqs;
    }

    public String getDescription() {
        return description;
    }

}
