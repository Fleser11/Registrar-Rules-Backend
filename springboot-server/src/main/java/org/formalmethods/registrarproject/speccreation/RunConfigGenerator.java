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
            if(pathway.get(i).getCourses().size()>0){
                SemConfig semConfig = pathway.get(i);
                sb.append("("+String.join(" + ", getSemInstances(semConfig.getCourses()))+") in semCourses");
                for(int j = 0; j < i; j++) {sb.append("'");}
                sb.append("\n");
            }
            if(pathway.get(i).getNumCourses() != null){
                sb.append("#semCourses");
                for(int j = 0; j < i; j++) {sb.append("'");}
                sb.append(" <= " + pathway.get(i).getNumCourses()+"\n");
            }
            else{
                sb.append("#semCourses");
                for(int j = 0; j < i; j++) {sb.append("'");}
                sb.append(" <= " + 6 +"\n");
            }
        }
        sb.append("\n}\n");
        return sb.toString();
        
    }

    private List<String> getSemInstances(List<String> courses){
        List<String> instances = new LinkedList<>();
        for(int i = 0; i < courses.size(); i++){
            String course = courses.get(i);
            if(course.contains("abstract_")){
                instances.add(this.instances.getInstance(course));
            }
            else{
                instances.add(course);
            }
        }
        return instances;
    }
}
