package org.formalmethods.registrarproject.speccreation;

import java.util.List;

import org.openapitools.model.Audit;
import org.openapitools.model.RunConfig;
import org.openapitools.model.SemConfig;

public class RunConfigGenerator {
    
    private RunConfig runConfig;

    public RunConfigGenerator(RunConfig runConfig) {
        this.runConfig = runConfig;
    }

    public String getRunConfigString() {
        StringBuilder sb = new StringBuilder("fact {\n");

        List<SemConfig> pathway = runConfig.getPathway();

        for (int i = 0; i < pathway.size(); i++) {
            if(pathway.get(i).getCourses().size()>0){
                SemConfig semConfig = pathway.get(i);
                sb.append("("+String.join(" + ", semConfig.getCourses())+") in semCourses");
                for(int j = 0; j < i; j++) {sb.append("'");}
                sb.append("\n");
            }
        }
        sb.append("\n}\n");
        return sb.toString();
        
    }
}
