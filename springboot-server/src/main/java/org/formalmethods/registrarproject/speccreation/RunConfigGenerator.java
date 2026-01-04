package org.formalmethods.registrarproject.speccreation;

import org.formalmethods.registrarproject.speccreation.AbstractInstancesGenerator.AbstractInstances;

import java.util.List;
import java.util.LinkedList;

import org.openapitools.model.Audit;
import org.openapitools.model.RunConfig;
import org.openapitools.model.SemConfig;

public class RunConfigGenerator {

    private RunConfig runConfig;
    private AbstractInstances instances;

    public RunConfigGenerator(RunConfig runConfig, AbstractInstances instances) {
        this.runConfig = runConfig;
        this.instances = instances;
    }

    public String getRunConfigString() {
        StringBuilder sb = new StringBuilder("fact {\n");

        List<SemConfig> pathway = runConfig.getPathway();

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
            } else {
                sb.append("#semCourses");
                for (int j = 0; j < i; j++) {
                    sb.append("'");
                }
                sb.append(" <= 6 \n");
            }
        }

        //Creates transfer course set
        if(runConfig.getTransferCourses() != null && runConfig.getTransferCourses().size() > 0){
            sb.append("transferCourses = (");
            sb.append(String.join(" + ", getSemInstances(runConfig.getTransferCourses())));
            sb.append(")\n");
        }
        else{
            sb.append("no transferCourses\n");
        }

        sb.append("\n}\n");
        return sb.toString();

    }

    private List<String> getSemInstances(List<String> courses) {
        List<String> instances = new LinkedList<>();
        for (int i = 0; i < courses.size(); i++) {
            String course = courses.get(i);
            if (course.contains("abstract_")) {
                instances.add(this.instances.getInstance(course));
            } else {
                instances.add(course);
            }
        }
        return instances;
    }
}
