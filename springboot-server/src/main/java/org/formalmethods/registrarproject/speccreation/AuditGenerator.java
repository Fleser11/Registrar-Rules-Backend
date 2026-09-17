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
        
        pred prereqsMet[c: Course, pc: set Course]{
            not (no c.req) implies (
                c.req in And1 implies (
                    all and1Req: c.req.subAnd1 | (
                        and1Req in Course implies (once and1Req in pc or and1Req in transferCourses)
                            else (some or2Req: and1Req.subOr2 | (
                                or2Req in Course implies (once or2Req in pc or or2Req in transferCourses)
                                    else (all and3Req: or2Req.subAnd3 | (
                                        once and3Req in pc or and3Req in transferCourses
                                        )
                                    )
                                )
                            )

                    )
                )
                else (
                    some or1Req: c.req.subOr1 | (
                        or1Req in Course implies (once or1Req in pc or or1Req in transferCourses)
                            else (all and2Req: or1Req.subAnd2 | (
                                and2Req in Course implies (once and2Req in pc or and2Req in transferCourses)
                                    else (some or3Req: and2Req.subOr3 | (
                                        once or3Req in pc or or3Req in transferCourses
                                        )
                                    )
                                )
                            )

                    )
                )
            )
        //all p: c.prereqs | (once p in passedCourses or p in transferCourses)
        }


        pred doNothing{
            always semCourses' = semCourses
            and always passedCourses' = passedCourses
            and always transferCourses' = transferCourses
        }

        pred semester {
            (all c: semCourses' | prereqsMet[c, passedCourses])
            and (no c: semCourses' | once c in passedCourses)
        }

        fact{
            (no semCourses.req)
            and (passedCourses = semCourses)
            and (always Course' = Course)
            and (always passedCourses' = passedCourses + semCourses')
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

                ArrayList<String> allCoursesPrimitives = new ArrayList<>();
                Pattern coursePattern = Pattern.compile("([a-zA-Z][a-zA-Z]+[0-9]{4})(\\(C\\))?");
                for (String course: courses){
                    if (course.startsWith("abstract_")){
                        allCoursesPrimitives.add(formatCourseName(course));
                    }
                    else{
                        Matcher matcher = coursePattern.matcher(course);
                        while(matcher.find()){
                            System.out.print(course);
                            allCoursesPrimitives.add(matcher.group(1));
                        }
                    }
                }
                String inCourses = formatSubAuditName(audit, subAudit) + " in (" + String.join(" + ", allCoursesPrimitives) + ")\n";
                specBuilder.append(inCourses);

                
                for(int i = 0; i < subAudit.getCourses().size(); i++){
                    if(courses.get(i).startsWith("abstract_")){
                        specBuilder.append("and (one c: " + formatSubAuditName(audit, subAudit) + " | c in " + formatCourseName(courses.get(i)) + ")\n");
                    }
                    else{
                        Matcher matcher = coursePattern.matcher(courses.get(i));
                        String inSubAudit = matcher.replaceAll("($1 in " + formatSubAuditName(audit, subAudit) + ")");
                        specBuilder.append((i > 0 ? "and ":"") + "("+ inSubAudit + ")\n");
                    }
                }
            }
            else {
                specBuilder.append("#"+formatSubAuditName(audit, subAudit) + " = " + subAudit.getCardinality() + "\n");

                String inCourses = formatSubAuditName(audit, subAudit) + " in (" + String.join(" + ", subAudit.getCourses()) + ")\n";

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
        return course;
    }


    
}
