package org.formalmethods.registrarproject.alloy.alloycreation;



class CourseCollection {

    static class Course{

        private String dept;
        private int num;
        private String name;
        private float credits;
        private String sem;
        private String prereqs;

        public Course(String dept, int num, String name, float credits, String sem, String prereqs){
            this.dept = dept;
            this.num = num;
            this.name = name;
            this.credits = credits;
            this.sem = sem;
            this.prereqs = prereqs;
        }

        public String getDept() {
            return dept;
        }

        public int getNum() {
            return num;
        }

        public String getName() {
            return name;
        }

        public float getCredits() {
            return credits;
        }

        public String getSem() {
            return sem;
        }

        public String getPrereqs() {
            return prereqs;
        }

        public String getCourseAlloy(){
            return "";
        }

         
    }
}