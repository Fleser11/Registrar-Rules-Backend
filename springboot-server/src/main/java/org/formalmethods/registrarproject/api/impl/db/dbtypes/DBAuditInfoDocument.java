package org.formalmethods.registrarproject.api.impl.db.dbtypes;

public class DBAuditInfoDocument {
    int gid;
    String code;
    String program;
    boolean isGenEd;

    public DBAuditInfoDocument(int gid, String code, String program, boolean isGenEd) {
        this.gid = gid;
        this.code = code;
        this.program = program;
        this.isGenEd = isGenEd;
    }
    public int getGid() {
        return gid;
    }
    public String getCode() {
        return code;
    }

    public String getProgram() {
        return program;
    }

    public boolean isGenEd() {
        return isGenEd;
    }

}
