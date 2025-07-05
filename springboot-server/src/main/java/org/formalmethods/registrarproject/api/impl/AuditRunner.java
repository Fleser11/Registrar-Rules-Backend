package org.formalmethods.registrarproject.api.impl;

import java.util.List;
import java.util.ArrayList;

import org.formalmethods.registrarproject.alloy.AlloyRunner;
import org.formalmethods.registrarproject.alloy.AlloyRunner.AlloyResult;
import org.openapitools.model.Audit;
import org.openapitools.model.RunConfig;
import org.openapitools.model.SemConfig;

public class AuditRunner {
    /**
     * Class that wraps operations from the Alloy Runner to perform the audit
     */


    private String alloySpec = null;
    private AlloyRunner alloyRunner = null;

    public AuditRunner(Audit audit) {
        this.alloySpec = getAlloySpec(audit);
        this.alloyRunner = new AlloyRunner();
    }

    public void runAudit(RunConfig config) {
        
    }

    public List<SemConfig> getResult() {
        //TODO: implement the logic that retrieves the result from AlloyRunner.
        List<SemConfig> pathway = null; //parseResult(AlloyRunner.runAlloySpec());
        return pathway;
    }

    private List<SemConfig> sparseResult(AlloyResult result) {
        //TODO: implement the logic to parse AlloyResult into List<SemConfig>
        return new ArrayList<SemConfig>();
    }

    private String getAlloySpec(Audit audit) {
        //TODO: implement the logic to generate Alloy specification from Audit
        return "";
    }

}