package org.formalmethods.registrarproject.speccreation;

import java.util.HashMap;
import java.util.LinkedList;

import org.openapitools.model.RunConfig;

/**
 * Class generates abstract course instances if they exist in the run config.
 * Need instantiated instances
 */
public class AbstractInstancesGenerator {

    public static class AbstractInstances {

        private HashMap<String, LinkedList<String>> abstractInstancesMap;

        public AbstractInstances() {
            abstractInstancesMap = new HashMap<String, LinkedList<String>>();
        }

        public void addAbstractInstance(String abstractCourse) {
            LinkedList<String> instances = abstractInstancesMap.get(abstractCourse);
            if (instances == null) {
                instances = new LinkedList<String>();
                abstractInstancesMap.put(abstractCourse, instances);
            }
            int size = instances.size();
            instances.add(abstractCourse + "_" + size);
        }

        public String getInstance(String abstractCourse) {
            LinkedList<String> instances = abstractInstancesMap.get(abstractCourse);
            if (instances == null) {
                throw new IllegalArgumentException("Abstract course " + abstractCourse + " not found.");
            }
            return instances.pop();
        }

        public HashMap<String, LinkedList<String>> get() {
            return abstractInstancesMap;
        }
    }

    private AbstractInstances abstractInstances;

    public AbstractInstancesGenerator(RunConfig runConfig) {
        abstractInstances = new AbstractInstances();
        parseAbstractInstances(runConfig);
    }

    public AbstractInstances getAbstractInstances() {
        return abstractInstances;
    }

    public String getAbstractInstancesString() {
        StringBuilder sb = new StringBuilder();
        HashMap<String, LinkedList<String>> map = abstractInstances.get();
        for (String abstractCourse : map.keySet()) {
            LinkedList<String> instances = map.get(abstractCourse);
            for (String instance : instances) {
                sb.append("one sig " + instance + " extends " + abstractCourse + " {}\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void parseAbstractInstances(RunConfig runConfig) {
        for (var semConfig : runConfig.getPathway()) {
            for (var course : semConfig.getCourses()) {
                if (course.contains("abstract_")) {
                    abstractInstances.addAbstractInstance(course);
                }
            }
        }
    }

}