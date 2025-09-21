package org.formalmethods.registrarproject.speccreation;

import java.util.ArrayList;

public class CommandGenerator {

    public String createCommandString(ArrayList<String> modelSubAudits) {
        String header = "pred complete {\n";
        StringBuilder bodyBuilder = new StringBuilder();
        
        String onceExistingTemplate = "(all c: %s | once c in passedCourses)\n";

        for (int i = 0; i < modelSubAudits.size(); i++) {
            if (i > 0)
                bodyBuilder.append("and ");
            bodyBuilder.append(String.format(onceExistingTemplate, modelSubAudits.get(i)));
        }
        bodyBuilder.append(String.format("and (disj[%s])\n}\n", String.join(",", modelSubAudits)));

        //This should essentially take the parameters and specify that the courses 
        String runLine = "run {eventually complete} for 74 Course, exactly 8 steps\n";
        return header + bodyBuilder.toString() + runLine;
    } 

}
