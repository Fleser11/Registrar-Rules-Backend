package org.formalmethods.registrarproject.api.impl;

import java.util.List;
import java.util.ArrayList;

import org.formalmethods.registrarproject.api.impl.alloy.AlloyRunner;
import org.formalmethods.registrarproject.api.impl.alloy.AlloyRunner.AlloyResult;


import org.openapitools.model.Audit;
import org.openapitools.model.RunConfig;
import org.openapitools.model.SemConfig;

public class AuditRunner {
    /**
     * Basic class that wraps audit operations for rest of service.
     */

    private Audit audit = null;
    private AlloyRunner alloyRunner = null;

    public AuditRunner(Audit audit) {
        this.audit = audit;
        this.alloyRunner = new AlloyRunner();
    }

    public void runAudit(RunConfig config) {
        
    }


    public List<SemConfig> getResult() {
        //TODO: implement the logic that retrieves the result from AlloyRunner.
        List<SemConfig> pathway = null; //parseResult(AlloyRunner.runAlloySpec());
        return pathway;
    }

    private List<SemConfig> parseResult(AlloyResult result) {
        //TODO: implement the logic to parse AlloyResult into List<SemConfig>
        return new ArrayList<SemConfig>();
    }

}