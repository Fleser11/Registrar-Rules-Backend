package org.formalmethods.registrarproject.api.impl;

import java.util.List;
import java.util.ArrayList;

import org.formalmethods.registrarproject.alloy.AlloyRunner;
import org.formalmethods.registrarproject.alloy.AlloyRunner.AlloyResult;
import org.openapitools.model.Audit;
import org.openapitools.model.RunConfig;
import org.openapitools.model.SemConfig;

import org.formalmethods.registrarproject.speccreation.*;
import org.formalmethods.registrarproject.alloy.AlloyRunner;

public class AuditRunner {
    /**
     * Class that wraps operations from the Alloy Runner to perform the audit
     */

    private AlloyRunner alloyRunner = null;
    private SpecCreator specCreator = null;

    private String alloySpec = null;
    private List<SemConfig> result = null;

    public AuditRunner() {
        this.alloyRunner = new AlloyRunner();
        this.specCreator = new SpecCreator();
    }

    public void runAudit(Audit primaryAudit, Audit genEdAudit, RunConfig config) {
        String alloySpec = specCreator.createSpec(primaryAudit, genEdAudit, config);
        result = parseResult(alloyRunner.runAlloySpec(alloySpec));
    }
    
    public List<SemConfig> getResult() {
        if (result == null) {
            throw new IllegalStateException("runAudit operation has not been performed yet.");
        }
        return result;
    }

    //TODO: implement next
    public void next() {
        if (result == null) {
            throw new IllegalStateException("runAudit operation has not been performed yet.");
        }
        result = parseResult(alloyRunner.runAlloySpec(alloySpec));
    }

    private List<SemConfig> parseResult(AlloyResult result) {
        //TODO: implement parsing
        System.out.println("Parsing result: " + result.getResult());
        return new ArrayList<>();
    }

}