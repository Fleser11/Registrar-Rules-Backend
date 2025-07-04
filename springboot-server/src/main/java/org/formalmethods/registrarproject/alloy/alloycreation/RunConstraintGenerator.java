package org.formalmethods.registrarproject.alloy.alloycreation;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class RunConstraintGenerator {
    /**
     * Generates the run constraints for the Alloy model
     * @param modelSubAudits
     * @return
     */
    public static String generateRunConstraints(ArrayList<String> modelSubAudits) {
        //TODO: move this to audit generator.
        String header = "pred complete {\n";
        StringBuilder bodyBuilder = new StringBuilder();
        
        String onceExistingTemplate = "all c: %s | once c in passedCourses\n";

        for (int i = 0; i < modelSubAudits.size(); i++) {
            bodyBuilder.append(String.format(onceExistingTemplate, modelSubAudits.get(i)));
        }
        bodyBuilder.append(String.format("disj[%s]\n}\n", String.join(",", modelSubAudits)));

        //This should essentially take the parameters and specify that the courses 
        String runLine = "run {eventually complete} for 74 Course, exactly 8 steps\n";
        return header + bodyBuilder.toString() + runLine;
    } 
}
