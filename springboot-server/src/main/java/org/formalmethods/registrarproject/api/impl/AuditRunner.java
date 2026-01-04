package org.formalmethods.registrarproject.api.impl;

import java.util.List;
import java.util.ArrayList;

import org.formalmethods.registrarproject.alloy.AlloyRunner;
import org.formalmethods.registrarproject.alloy.AlloyRunner.AlloyResult;
import org.formalmethods.registrarproject.alloy.AlloyRunner.AlloySuccessfulResult;
import org.formalmethods.registrarproject.alloy.AlloyRunner.AlloyFailedResult;

import org.openapitools.model.Audit;
import org.openapitools.model.RunConfig;
import org.openapitools.model.SemConfig;

import org.formalmethods.registrarproject.speccreation.*;
import org.formalmethods.registrarproject.alloy.AlloyRunner;

import org.formalmethods.registrarproject.db.DBService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuditRunner {
    /**
     * Class that wraps operations from the Alloy Runner to perform the audit
     */

    private AlloyRunner alloyRunner = null;
    private SpecCreator specCreator = null;

    private String alloySpec = null;
    private List<SemConfig> result = null;

    public AuditRunner(Audit primaryAudit, Audit genEdAudit, RunConfig config, DBService db) {
        this.alloyRunner = new AlloyRunner();
        this.specCreator = new SpecCreator(primaryAudit, genEdAudit, config, db);
    }

    public void runAudit() {
        String alloySpec = specCreator.getSpecString();
        AlloyResult alloyResult = alloyRunner.runAlloySpec(alloySpec);
        if (alloyResult instanceof AlloyFailedResult) {
            return;
        }
        else if (alloyResult instanceof AlloySuccessfulResult) {
            result = parseResult((AlloySuccessfulResult) alloyResult);
        }    
    }
    
    public List<SemConfig> getResult() {
        if (result == null) {
            throw new IllegalStateException("runAudit operation has not been performed yet.");
        }
        return result;
    }

    //TODO: implement next
    // public void next() {
    //     if (result == null) {
    //         throw new IllegalStateException("runAudit operation has not been performed yet.");
    //     }
    //     result = parseResult(alloyRunner.runAlloySpec(alloySpec));
    // }

    private List<SemConfig> parseResult(AlloySuccessfulResult result) {
        //TODO: implement parsing
        JSONObject json = result.getJsonResult();
        JSONArray steps = json.getJSONObject("alloy").getJSONArray("instance");

        List<SemConfig> semConfigs = new ArrayList<>();
        for (int i = 0; i < steps.length(); i++){
            SemConfig semConfig = new SemConfig();
            JSONArray stepSigs = steps.getJSONObject(i).getJSONArray("sig");
            //find this/semCourses
            for (int j = 0; j < stepSigs.length(); j++) {
                JSONObject sig = stepSigs.getJSONObject(j);
                if(sig.getString("label").equals("this/semCourses")){
                    Object atomObject = sig.opt("atom");
                    if (atomObject instanceof JSONArray) {
                        JSONArray courseJSONArr = (JSONArray) atomObject;
                        for(int k = 0; k < courseJSONArr.length(); k++) {
                                String courseName = courseJSONArr.getJSONObject(k).getString("label");
                                semConfig.addCoursesItem(cleanCourseName(courseName));
                            }
                    } else {
                        // if(atomObject != null){
                        //     System.out.println("Unexpected type for atom: " + atomObject.getClass().getName());
                        //     System.out.println("Value: " + atomObject.toString());
                        // }
                        // System.out.println("sig: " + sig.toString());
                        // System.out.println("step: " + steps.getJSONObject(i).toString());
                    }
  
                }
            }
            semConfig.setNumCourses(null);
            semConfigs.add(semConfig);
        }
        return semConfigs;
    }

    private String cleanCourseName(String courseName) {
        Pattern abPattern = Pattern.compile("(abstract_)?([a-zA-Z0-9]+)(_[0-9]+)?\\$[0-9]+");
        Matcher matcher = abPattern.matcher(courseName);
        if (matcher.matches()) {
            return matcher.group(1) != null ? matcher.group(1) + matcher.group(2) : matcher.group(2);
        }
        return courseName;
    }

}