package org.formalmethods.registrarproject.db.dbtypes;

public class DBAuditInfoDocument {
    String code;
    String program;
    boolean isGenEd;

    public DBAuditInfoDocument(String code, String program, boolean isGenEd) {
        this.code = code;
        this.program = program;
        this.isGenEd = isGenEd;
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
