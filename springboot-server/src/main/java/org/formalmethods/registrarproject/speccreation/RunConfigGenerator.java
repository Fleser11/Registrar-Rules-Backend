package org.formalmethods.registrarproject.speccreation;

import org.formalmethods.registrarproject.speccreation.AbstractInstancesGenerator.AbstractInstances;

import java.util.List;
import java.util.LinkedList;

import org.openapitools.model.Audit;
import org.openapitools.model.RunConfig;
import org.openapitools.model.SemConfig;
import org.openapitools.model.Rules;

public class RunConfigGenerator {

    private RunConfig runConfig;
    private AbstractInstances instances;

    public RunConfigGenerator(RunConfig runConfig, AbstractInstances instances) {
        this.runConfig = runConfig;
        this.instances = instances;
    }

    /**
     * Generates the Alloy spec string for the RunConfig object.
     * @return String representation of the RunConfig in Alloy spec format.
     */
    public String getRunConfigString() {
        StringBuilder sb = new StringBuilder("fact {\n");

        List<SemConfig> pathway = runConfig.getPathway().getSemesters();

        for (int i = 0; i < pathway.size(); i++) {
            //Add courses for the semester if there are any
            if (pathway.get(i).getCourses().size() > 0) {
                SemConfig semConfig = pathway.get(i);
                sb.append("(" + String.join(" + ", getSemInstances(semConfig.getCourses())) + ") in semCourses");
                for (int j = 0; j < i; j++) {
                    sb.append("'");
                }
                sb.append("\n");
            }

            //Add number of courses for the semester if they are available
            if (pathway.get(i).getNumCourses() != null) {
                sb.append("#semCourses");
                for (int j = 0; j < i; j++) {
                    sb.append("'");
                }
                sb.append(" <= " + pathway.get(i).getNumCourses() + "\n");
            }

            if (i != 0){
                sb.append("semCourses");
                for(int j = 0; j < i; j++){
                    sb.append("'");
                }
                sb.append(" != semCourses");
                for(int j = 0; j < i-1; j++){
                    sb.append("'");
                }
            }
            
        }

        //Creates transfer course set
        if(runConfig.getTransferCourses() != null && runConfig.getTransferCourses().size() > 0){
            
            List<String> transferCourseList = getSemInstances(runConfig.getTransferCourses());
            sb.append("transferCourses = (");
            sb.append(String.join(" + ", transferCourseList));
            sb.append(")\n");
        }
        else{
            sb.append("no transferCourses\n");
        }

        sb.append("\n}\n");

        Rules rules = runConfig.getRules();

        int minCourses = 4;
        int maxCourses = 6;

        if (rules != null){
            if (rules.getMinSemCourses() != null) {
                minCourses = rules.getMinSemCourses();
            } else {
                System.out.println("WARNING: No minSemCourses specified in rules. defaulting to 4");
            }
            if (rules.getMaxSemCourses() != null) {
                maxCourses = rules.getMaxSemCourses();
            } else {
                System.out.println("WARNING: No maxSemCourses specified in rules. defaulting to 6");
            }
        }
        else{
            System.out.println("WARNING: No rules applied for sem courses. defaulting to min 4, max 6");
        }

        String numCourseFactString = String.format("fact{\n"
                +"always (#semCourses >= %d)\n"
                +"and always (#semCourses <= %d)\n"
                +"}", minCourses, maxCourses);
        sb.append(numCourseFactString);



        return sb.toString();

    }

    private List<String> getSemInstances(List<String> courses) {
        List<String> instances = new LinkedList<>();
        for (int i = 0; i < courses.size(); i++) {
            String course = courses.get(i);
            instances.add(getCourseInstanceString(course));
        }
        return instances;
    }

    private String getCourseInstanceString(String courseName){
        if (courseName.contains("abstract_")){
            return this.instances.getInstance(courseName);
        }
        else {
            return courseName;
        }

    }
}
