package org.formalmethods.registrarproject.db.dbtypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import org.formalmethods.registrarproject.db.dbtypes.DBSubAuditDocument;
import org.bson.types.ObjectId;
import org.formalmethods.registrarproject.db.dbtypes.DBAuditInfoDocument;

@Document(collection = "audits")
public class DBAuditDocument {
    @Id
    private ObjectId _id;
    private String code;
    
    private DBAuditInfoDocument auditInfo;
    private List<DBSubAuditDocument> subAudits;

    public DBAuditDocument(String code, DBAuditInfoDocument auditInfo, List<DBSubAuditDocument> subAudits) {
        this.code = code;
        this.auditInfo = auditInfo;
        this.subAudits = subAudits;
    }

    public ObjectId get_Id() {
        return _id;
    }
    public String getCode() {
        return code;
    }
    public DBAuditInfoDocument getAuditInfo() {
        return auditInfo;
    }
    public List<DBSubAuditDocument> getSubAudits() {
        return subAudits;
    }
}
