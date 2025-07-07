package org.formalmethods.registrarproject.db.repositoryinterfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.formalmethods.registrarproject.db.dbtypes.DBCourseDocument;

import java.util.List;

public interface CoursesRepository extends MongoRepository<DBCourseDocument, String> {
    DBCourseDocument findByCode(String code);
    List<DBCourseDocument> findAll();
}