package org.formalmethods.registrarproject.speccreation;

import org.openapitools.model.Audit;
import org.openapitools.model.RunConfig;

import java.util.ArrayList;

import org.formalmethods.registrarproject.db.DBService;
// import org.formalmethods.registrarproject.speccreation.AuditGenerator;
// import org.formalmethods.registrarproject.speccreation.CourseSpecGenerator;
// import org.formalmethods.registrarproject.speccreation.RunConfigGenerator;
// import org.formalmethods.registrarproject.speccreation.RunCommandGenerator;

public class SpecCreator {
    
    //TODO: create a mirror class to decouple
    Audit primaryAudit;
    Audit genEdAudit;
    RunConfig runConfig;
    DBService db;
    
    /**
     * Aggregator for all of the spec creation steps.
     */
    public SpecCreator(Audit primaryAudit, Audit genEdAudit, RunConfig runConfig, DBService db) {
        this.primaryAudit = primaryAudit;
        this.genEdAudit = genEdAudit;
        this.runConfig = runConfig;
        this.db = db;
    }

    public String getSpecString(){
        CourseSpecGenerator courseSpecGenerator = new CourseSpecGenerator(primaryAudit, genEdAudit, db);
        String courseSpec = courseSpecGenerator.getCourseSpec();

        AuditGenerator auditGenerator = new AuditGenerator(primaryAudit, genEdAudit);
        String auditSpec = auditGenerator.generateSpecString();



        RunConfigGenerator runConfigGenerator = new RunConfigGenerator(runConfig);
        String runConfigSpec = runConfigGenerator.getRunConfigString();

        CommandGenerator runCommandGenerator = new CommandGenerator();
        ArrayList<String> subAuditNames = auditGenerator.getSubAuditNames();
        String runCommandSpec = runCommandGenerator.createCommandString(subAuditNames);

        String specString = courseSpec + "\n" + auditSpec + "\n" + runConfigSpec + "\n" + runCommandSpec;
        System.out.print(specString);
        return specString;
    }

    /**
     * Does allow for mutation of the primary audit, gen ed audit, and run config.
     */
    public void setPrimaryAudit(Audit primaryAudit) {
        this.primaryAudit = primaryAudit;
    }
    public void setGenEdAudit(Audit genEdAudit) {
        this.genEdAudit = genEdAudit;
    }
    public void setRunConfig(RunConfig runConfig) {
        this.runConfig = runConfig;
    }
}
