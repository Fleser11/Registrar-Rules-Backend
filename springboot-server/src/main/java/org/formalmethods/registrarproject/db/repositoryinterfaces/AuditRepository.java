package org.formalmethods.registrarproject.db.repositoryinterfaces;

import java.util.Optional;
import org.bson.types.ObjectId;

import org.formalmethods.registrarproject.db.dbtypes.DBAuditDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<DBAuditDocument, ObjectId> {
    Optional<DBAuditDocument> findById(ObjectId gid);
    Optional<DBAuditDocument> findByName(String name);
}
