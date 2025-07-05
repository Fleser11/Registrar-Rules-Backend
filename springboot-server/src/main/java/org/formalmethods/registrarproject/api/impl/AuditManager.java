package org.formalmethods.registrarproject.api.impl;


import org.formalmethods.registrarproject.db.dbtypes.DBAuditDocument;
import org.formalmethods.registrarproject.db.dbtypes.DBSubAuditDocument;
import org.formalmethods.registrarproject.db.dbtypes.DBAuditInfoDocument;
import org.openapitools.model.Audit;
import org.openapitools.model.AuditInfo;
import org.openapitools.model.SubAudit;
import org.springframework.stereotype.Component;
import org.formalmethods.registrarproject.db.DBService;

import org.bson.types.ObjectId;


import java.util.ArrayList;

//TODO: add input checking and error handling
@Component
public class AuditManager {

    private DBService dbService;

    public AuditManager(DBService dbService) {
        this.dbService = dbService;
    }

    public Audit getAudit(String auditCode){
        if (ObjectId.isValid(auditCode)){
            try {
                return getAuditFromGid(auditCode);
            } catch (Exception e) {
                return getAuditFromCode(auditCode);
            }
        }
        else {
            return getAuditFromCode(auditCode);
        }
    }
    
    /**
     * Gets the stored Model with the specified name.
     * @param modelName
     * @return The model.
     */
    public Audit getAuditFromCode(String modelCode) {
        DBAuditDocument dbAudit = dbService.getAuditByCode(modelCode);
        return parseToAudit(dbAudit);
    }

    /**
     * Gets the stored Model with the specified gid.
     * @param modelGid
     * @return
     */
    public Audit getAuditFromGid(String modelGid) {
        DBAuditDocument dbAudit = dbService.getAuditByGid(modelGid);
        return parseToAudit(dbAudit);
    }

    /**
     * Stores the given audit in the database.
     * @param audit The audit to store.
     * @return The stored audit.
     */
    public Audit storeAudit(Audit audit) {
        DBAuditDocument dbAudit = parseToDBAuditDocument(audit);
        dbService.storeAudit(dbAudit);
        return audit;
    }

    /**
     * Converts an Audit object to a DBAuditDocument for storage in the database.
     * @param audit The Audit object to convert.
     * @return The corresponding DBAuditDocument.
     */
    public static DBAuditDocument parseToDBAuditDocument(Audit audit) {

        //Creates DBSubAuditDocument objects from the Audit sub-audits
        ArrayList<DBSubAuditDocument> subAudits = new ArrayList<>();
        audit.getSubAudit().forEach(subAudit -> {
            DBSubAuditDocument dbSubAudit = new DBSubAuditDocument(
                subAudit.getName(),
                subAudit.getCardinality(),
                subAudit.getCourses()
            );
            subAudits.add(dbSubAudit);
        });

        //Creates DBAuditInfoDocument from the Audit info
        DBAuditInfoDocument auditInfo = new DBAuditInfoDocument(
            audit.getInfo().getCode(),
            audit.getInfo().getProgram(),
            audit.getInfo().getIsGenEd()
        );


        //Creats DBAuditDocument from the Audit
        DBAuditDocument dbAudit = new DBAuditDocument(
            audit.getInfo().getCode(),
            auditInfo,
            subAudits
        );
        return dbAudit;
    }

    /**
     * Converts a DBAuditDocument to an Audit object.
     * @param dbAudit The DBAuditDocument to convert.
     * @return The corresponding Audit object.
     */
    public static Audit parseToAudit(DBAuditDocument dbAudit) {

        //Creates Audit sub-audit objects from the DBSubAuditDocument
        AuditInfo auditInfo = new AuditInfo();
        auditInfo.setGid(dbAudit.get_Id().toHexString());
        auditInfo.setCode(dbAudit.getCode());
        auditInfo.setProgram(dbAudit.getAuditInfo().getProgram());
        auditInfo.setIsGenEd(dbAudit.getAuditInfo().isGenEd());

        //Creates AuditInfo object from the DBAuditInfoDocument
        ArrayList<SubAudit> subAudits = new ArrayList<>();
        dbAudit.getSubAudits().forEach(dbSubAudit -> {
            SubAudit subAudit = new SubAudit();
            subAudit.setName(dbSubAudit.getName());
            subAudit.setCardinality(dbSubAudit.getCardinality());
            subAudit.setCourses(dbSubAudit.getCourses());
            subAudits.add(subAudit);
        });

        //Creates Audit object from the AuditInfo and sub-audits
        Audit audit = new Audit();
        audit.setInfo(auditInfo);
        audit.setSubAudit(subAudits);
        return audit;
    }

}
