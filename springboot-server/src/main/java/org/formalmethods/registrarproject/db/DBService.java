package org.formalmethods.registrarproject.db;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.formalmethods.registrarproject.db.dbtypes.DBAuditDocument;
import org.formalmethods.registrarproject.db.dbtypes.DBCourseDocument;
import org.formalmethods.registrarproject.db.repositoryinterfaces.AuditRepository;
import org.formalmethods.registrarproject.db.repositoryinterfaces.CoursesRepository;
import org.formalmethods.registrarproject.exception.InvalidInputException;

@Service
public class DBService {
    /**
     * Handles interactions with the database for audits and courses.
     */

    private AuditRepository auditRepository;
    private CoursesRepository coursesRepository;


    public DBService(AuditRepository auditRepo, CoursesRepository coursesRepo) {
        this.auditRepository = auditRepo;
        this.coursesRepository = coursesRepo;
    }
    /**
     * Retrieves a audit from the database using the name
     * @param name
     * @return A database audit document
     */
    public DBAuditDocument getAuditByCode(String code) {
        Optional <DBAuditDocument> auditOpt = auditRepository.findByCode(code);
        if (auditOpt.isPresent()) {
            return auditOpt.get();
        }
        else {
            throw new InvalidInputException("Audit with code " + code + " not found.");
        }
    }

    /**
     * Retrieves a list of audits from the database
     * @return A list of database audit documents
     */
    public DBAuditDocument getAuditByGid(String gid) {
        //attempts to convert gid to ObjectID
        Optional<DBAuditDocument> auditOpt = auditRepository.findById(new ObjectId(gid));
        if (auditOpt.isPresent()) {
            return auditOpt.get();
        } else {
            throw new InvalidInputException("Audit with gid " + gid + " not found.");
        }
    }

    /**
     * Stores a audit in the database
     * @param audit to be stored
     * @return The stored audit document
     */
    public DBAuditDocument storeAudit(DBAuditDocument audit) {
        return auditRepository.save(audit);
    }

    /**
     * Retrieves a course from the database
     * @param code The code of the course to retrieve
     * @return The specified course from the database
     */
    public DBCourseDocument getCourse(String code) {
        return coursesRepository.findByCode(code);
    }


}
