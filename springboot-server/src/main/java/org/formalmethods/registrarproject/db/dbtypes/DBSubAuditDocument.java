package org.formalmethods.registrarproject.db.dbtypes;

import java.util.List;

public class DBSubAuditDocument {
    String name;
    String cardinality;
    List<String> courses;

    public DBSubAuditDocument(String name, String cardinality, List<String> courses) {
        this.name = name;
        this.cardinality = cardinality;
        this.courses = courses;
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
}
