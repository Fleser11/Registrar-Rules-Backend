package org.formalmethods.registrarproject.api.impl.db.dbtypes;

import org.openapitools.model.AuditInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import org.formalmethods.registrarproject.api.impl.db.dbtypes.DBSubAuditDocument;

@Document(collection = "audits")
public class DBAuditDocument {
    @Id
    private int gid;
    private String name;
    
    private AuditInfo auditInfo;
    private List<DBSubAuditDocument> subAudits;

    public DBAuditDocument(int gid, String name, AuditInfo auditInfo, List<DBSubAuditDocument> subAudits) {
        this.gid = gid;
        this.name = name;
        this.auditInfo = auditInfo;
        this.subAudits = subAudits;
    }

    public int getGid() {
        return gid;
    }
    public String getName() {
        return name;
    }
    public AuditInfo getAuditInfo() {
        return auditInfo;
    }
    public List<DBSubAuditDocument> getSubAudits() {
        return subAudits;
    }
}
