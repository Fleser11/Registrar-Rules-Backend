package org.formalmethods.registrarproject.speccreation;

import org.openapitools.model.Audit;
import org.openapitools.model.SubAudit;
import org.openapitools.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuditGenerator {

    static final String AUDIT_HEADER = """
        pred prereqsMet[c: Course]{
        all p: c.prereqs | once p in passedCourses
        }

        pred doNothing{
            (#semCourses <= 6)
            and (always semCourses' = semCourses)
        }

        pred semester {
            all c: semCourses' | prereqsMet[c]
            and (no k : semCourses' | once k in semCourses)
        }

        fact{
            (no semCourses.prereqs)
            and (always #semCourses <= 6)
            and (always #semCourses >= 4)
            and (semCourses' != semCourses)
            and (always Course' = Course)
            and (always(
                semester
                or
                always doNothing
            ))
        }

        """;

    private Audit audit;
    private Audit genEdAudit;

    public AuditGenerator(Audit audit, Audit genEdAudit) {
        this.audit = audit;
        this.genEdAudit = genEdAudit;
    }


    public String generateSpecString() {
        StringBuilder specBuilder = new StringBuilder(AUDIT_HEADER);
        specBuilder.append(buildSpecSubAudits(audit));
        specBuilder.append(buildSpecSubAudits(genEdAudit));
        return specBuilder.toString();
    }

    private String buildSpecSubAudits(Audit audit){
        StringBuilder specBuilder = new StringBuilder();
        // Add the requirements for each subAudit
        audit.getSubAudit().forEach(subAudit -> {
            specBuilder.append("sig " + formatSubAuditName(audit, subAudit) + " in Course {}\nfact {\n");

            if (subAudit.getCardinality().equals("ALL")){
                ArrayList<String> courses = (ArrayList<String>) subAudit.getCourses();
                
                for(int i = 0; i < subAudit.getCourses().size(); i++){
                    if(courses.get(i).startsWith("abstract_")){
                        specBuilder.append((i > 0 ? "and ":"") + "(some c: " + formatCourseName(courses.get(i)) + " | c in " + formatSubAuditName(audit, subAudit) + ")\n");
                    }
                    else{
                        Pattern coursePattern = Pattern.compile("([a-zA-Z][a-zA-Z]+[0-9]{4})(\\(C\\))?");
                        Matcher matcher = coursePattern.matcher(courses.get(i));
                        String inSubAudit = matcher.replaceAll("($1 in " + formatSubAuditName(audit, subAudit) + ")");
                        specBuilder.append((i > 0 ? "and ":"") + "("+ inSubAudit + ")\n");
                    }
                }
            }
            else {
                specBuilder.append("#"+formatSubAuditName(audit, subAudit) + " = " + subAudit.getCardinality() + "\n");

                String inCourses = formatSubAuditName(audit, subAudit) + " in (" + String.join(" + ", subAudit.getCourses()) + ")\n";

                // Pattern abstractPattern = Pattern.compile("(abstract_[a-zA-Z0-9]*)");
                // Matcher matcher = abstractPattern.matcher(subAudit.getCourses().toString());

                // inCourses = matcher.replaceAll(audit.getInfo().getCode() + "_$1");

                specBuilder.append(inCourses);
            }
            specBuilder.append("}\n");
        });

        return specBuilder.toString();
    }
    /**
     * Gets all of the names of the subAudits.
     * @return The subAudit names
     */
    public ArrayList<String> getSubAuditNames(){
        ArrayList<String> subAuditNames = new ArrayList<>();
        audit.getSubAudit().forEach(subAudit -> {
            subAuditNames.add(formatSubAuditName(audit, subAudit));
        });
        genEdAudit.getSubAudit().forEach(subAudit -> {
            subAuditNames.add(formatSubAuditName(genEdAudit, subAudit));
        });
        return subAuditNames;
    }

    /**
     * String cleaning and formatting for the requirements string.
     * @param reqs
     * @param auditFile
     * @param subAudit
     * @return
     */
    private String formatReqsString(String reqs, String auditFile, String subAudit){
        //replaces
        Pattern cPostfixPattern = Pattern.compile("(?<!abstract_)([A-Za-z][A-Za-z]+[0-9]{4})(\\(C\\))?");
        Matcher matcher = cPostfixPattern.matcher(reqs);
        String cPostfixRemoved = matcher.replaceAll("$1");

        return cPostfixRemoved;
    }


    //TODO: Uncouple the naming schemes between the Audit generator and the Course Generator
    /**
     * Formats the subAudit name to include the audit code.
     * @param subAudit
     * @return The formatted subAudit name
     */
    private String formatSubAuditName(Audit audit, SubAudit subAudit) {
        String auditCode = audit.getInfo().getCode();
        return (auditCode + "_" + subAudit.getName());
    }


    //TODO: set up so that it actually uses the audit code
    /**
     * Formats the course name to include the audit code.
     * @param course
     * @return
     */
    private String formatCourseName(String course) {
        // String auditCode = audit.getInfo().getCode();
        // return (auditCode + "_" + course);
        return course;
    }


    
}
