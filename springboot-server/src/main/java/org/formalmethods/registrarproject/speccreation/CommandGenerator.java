package org.formalmethods.registrarproject.speccreation;

import java.util.ArrayList;

import org.openapitools.model.RunConfig;
import org.openapitools.model.Rules;

public class CommandGenerator {

    public String createCommandString(ArrayList<String> modelSubAudits, RunConfig runConfig) {

        int numSteps = runConfig.getPathway().getSemesters().size();

        Rules rules = runConfig.getRules();

        boolean minimization = false;
        if (rules != null){
            Boolean minimizationValue = rules.getMinimization();
            if (minimizationValue != null) {
                minimization = minimizationValue;
            }
            else {
                System.out.print("WARNING: no minimization specified, defaulting to false");
            }
        }
        else {
            System.out.print("WARNING: no rules defined with minimization, defaulting to false");
        }

        String header = "pred complete[p: set Course] {\n";
        StringBuilder bodyBuilder = new StringBuilder();

        String onceExistingTemplate = "(all c: %s | (c in p))\n";

        for (int i = 0; i < modelSubAudits.size(); i++) {
            if (i > 0)
                bodyBuilder.append("and ");
            bodyBuilder.append(String.format(onceExistingTemplate, modelSubAudits.get(i)));
        }
        bodyBuilder.append(String.format("and (disj[%s])\n}\n", String.join(",", modelSubAudits)));

        //This should essentially take the parameters and specify that the courses

        String runLine = String.format(
            """
                run {eventually (complete[passedCourses + transferCourses] 
                %s  
                )} for 74 Course, 9 Or1, 28 And1, 7 Or2, 0 And2, 0 Or3, 0 And3, exactly %d steps, 5 int
                """, 
                minimization ? " and ( \n"
                        + "no p: passedCourses | (\n"
                        + "(complete[(passedCourses-p) + transferCourses])\n"
                        + "and \n" 
                        + "(all p2: passedCourses | prereqsMet[p2, passedCourses-p])\n"
                    +")\n)\n" : "", 
                numSteps);
        return header + bodyBuilder.toString() + runLine;
    }

}
