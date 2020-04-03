package com.example.unifind;

public class Program {
    protected String name;
    protected double admission_average;
    protected double local_tuition;
    protected double international_tuition;
    protected String requirements;
    protected boolean coop;
    protected String target_enrolment;
    protected boolean supplementary_applicatoin;

    public Program(String p, double a, double lt, double it, String r, boolean c, String t, boolean s) {
        name = p;
        admission_average = a;
        local_tuition = lt;
        international_tuition = it;
        requirements = r;
        coop = c;
        target_enrolment = t;
        supplementary_applicatoin = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAdmission_average() {
        return admission_average;
    }

    public void setAdmission_average(double admission_average) {
        this.admission_average = admission_average;
    }

    public double getLocal_tuition() {
        return local_tuition;
    }

    public void setLocal_tuition(double local_tuition) {
        this.local_tuition = local_tuition;
    }

    public double getInternational_tuition() {
        return international_tuition;
    }

    public void setInternational_tuition(double international_tuition) {
        this.international_tuition = international_tuition;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public boolean isCoop() {
        return coop;
    }

    public void setCoop(boolean coop) {
        this.coop = coop;
    }

    public String getTarget_enrolment() {
        return target_enrolment;
    }

    public void setTarget_enrolment(String target_enrolment) {
        this.target_enrolment = target_enrolment;
    }

    public boolean isSupplementary_applicatoin() {
        return supplementary_applicatoin;
    }

    public void setSupplementary_applicatoin(boolean supplementary_applicatoin) {
        this.supplementary_applicatoin = supplementary_applicatoin;
    }
}
