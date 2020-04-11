package com.example.unifind;


public class Program {

    protected String name;
    protected int admission_average;
    protected int local_tuition;
    protected int international_tuition;
    protected String requirements;
    protected boolean coop;
    protected String target_enrolment;
    protected boolean supplementary_applicatoin;

    // constructors
    public Program(String p, int a, int lt, int it, String r, boolean c, String t, boolean s) {
        name = p;
        admission_average = a;
        local_tuition = lt;
        international_tuition = it;
        requirements = r;
        coop = c;
        target_enrolment = t;
        supplementary_applicatoin = s;
    }

    // getter for name
    public String getName() {
        return name;
    }

    // setter for name
    public void setName(String name) {
        this.name = name;
    }

    // getter for admission_average
    public int getAdmission_average() {
        return admission_average;
    }

    // setter for admission_average
    public void setAdmission_average(int admission_average) {
        this.admission_average = admission_average;
    }

    // getter for local_tuition
    public int getLocal_tuition() {
        return local_tuition;
    }

    // setter for local_tuition
    public void setLocal_tuition(int local_tuition) {
        this.local_tuition = local_tuition;
    }

    // getter for international_tuition
    public int getInternational_tuition() {
        return international_tuition;
    }

    // setter for international_tuition
    public void setInternational_tuition(int international_tuition) {
        this.international_tuition = international_tuition;
    }

    // getter for requirements
    public String getRequirements() {
        return requirements;
    }

    // setter for requirements
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    // getter for coop
    public boolean isCoop() {
        return coop;
    }

    // setter for coop
    public void setCoop(boolean coop) {
        this.coop = coop;
    }

    // getter for target_enrolment
    public String getTarget_enrolment() {
        return target_enrolment;
    }

    // setter for target_enrolment
    public void setTarget_enrolment(String target_enrolment) {
        this.target_enrolment = target_enrolment;
    }

    // getter for supplementary_application
    public boolean isSupplementary_applicatoin() {
        return supplementary_applicatoin;
    }

    // setter for supplementary application
    public void setSupplementary_applicatoin(boolean supplementary_applicatoin) {
        this.supplementary_applicatoin = supplementary_applicatoin;
    }
}
