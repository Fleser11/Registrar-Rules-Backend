package org.formalmethods.registrarproject.db.dbtypes;

import java.util.List;

public class DBSubAuditDocument {
    String name;
    String cardinality;
    List<String> courses;
    String description;

    public DBSubAuditDocument(String name, String cardinality, List<String> courses, String description) {
        this.name = name;
        this.cardinality = cardinality;
        this.courses = courses;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getCardinality() {
        return cardinality;
    }
    public List<String> getCourses() {
        return courses;
    }

    public String getDescription(){
        return description;
    }
}
