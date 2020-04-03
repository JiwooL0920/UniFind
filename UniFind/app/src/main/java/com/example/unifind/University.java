package com.example.unifind;
import java.util.ArrayList;

public class University {
    private String name;
    private ArrayList<Program> programs;
    private int ranking;

    //Constructor
    public University(String name) {
        this.name = name;
        this.programs = new ArrayList<Program>();
        this.ranking = 0; //null
    }

    //Getter for name
    public String getName() { return this.name; }

    //Getter for programs
    public ArrayList<Program> getPrograms() { return this.programs; }

    //Getter for ranking
    public int getRanking() { return this.ranking; }

    //Setter for ranking
    public void setRanking(int n) { this.ranking = n; }

    //Setter for programs (add a program to arraylist)
    public void addProgram(Program p) { this.programs.add(p); }

    //return true if program exists in university
//    public boolean hasProgram(String name) {
//        for (Program p : this.programs) {
//            if (p.getName().equals(name)) return true;
//        } return false;
//    }

    //return program in the arraylist given name
//    public Program getPrograms(String name) {
//        if (!hasProgram(name)) return null;
//        for (Program p : this.programs) {
//            if (p.getName().equals(name)) return p;
//        }
//    }

}
