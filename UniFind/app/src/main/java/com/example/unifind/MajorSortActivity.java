package com.example.unifind;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

import android.util.Log;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MajorSortActivity extends AppCompatActivity {
    public String[] universityFileNames;
    public ArrayList<University> universities;
    private HashMap<String,String> rankingList;
    private ArrayList<String> boolList;
    private String[] categories;
    private HashMap<String,String> universityNameConversion;
    private String major;

    //Expandable view
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listItem;
    MainAdaptor adaptor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_sort);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize field variables
        this.universityFileNames = new String[] {"algoma", "brock", "carleton",
                "guelph", "hearst", "lakehead",
                "laurentian", "mcmaster", "nipissing",
                "ocad", "uoit", "ottawa",
                "queens", "ryerson", "trent",
                "uoft", "waterloo", "western",
                "wilfred_laurier", "windsor", "york"};

        this.categories = new String[] {"admission_average",              // 0
                "local_tuition",                                          // 1
                "international_tuition",                                  // 2
                "coop",                                                   // 3
                "target_enrolment",                                       // 4
                "supplementary_application"};                             // 5

        //University name conversion
        this.universityNameConversion = new HashMap<String,String>();
        this.universityNameConversion.put("algoma","Algoma University");
        this.universityNameConversion.put("brock","Brock University");
        this.universityNameConversion.put("carleton","Carleton University");
        this.universityNameConversion.put("guelph","University of Guelph");
        this.universityNameConversion.put("hearst","Université de Hearst");
        this.universityNameConversion.put("lakehead","Lakehead University");
        this.universityNameConversion.put("laurentian","Laurentian University");
        this.universityNameConversion.put("mcmaster","McMaster University");
        this.universityNameConversion.put("nipissing","Nipissing University");
        this.universityNameConversion.put("ocad","OCAD University");
        this.universityNameConversion.put("uoit","University of Ontario Institute of Technology");
        this.universityNameConversion.put("ottawa","University of Ottawa");
        this.universityNameConversion.put("queens","Queen's University");
        this.universityNameConversion.put("ryerson","Ryerson University");
        this.universityNameConversion.put("trent","Trent University");
        this.universityNameConversion.put("uoft","University of Toronto");
        this.universityNameConversion.put("waterloo","University of Waterloo");
        this.universityNameConversion.put("western","Western University");
        this.universityNameConversion.put("wilfred_laurier","Wilfred Laurier University");
        this.universityNameConversion.put("windsor","University of Windsor");
        this.universityNameConversion.put("york","York University");

        this.universities = new ArrayList<>();
        this.rankingList = new HashMap<>();
        this.boolList = new ArrayList<>();

        //Get Data
        getData();

        //Retrieved data (what major) passed from MajorActivity
        this.major = getIntent().getStringExtra("Major");
        Log.i("myapp",this.major);
        TextView majorNameTextView = findViewById(R.id.majorName);
        majorNameTextView.setText(this.major);

        //Expandable View
        expandableListView = findViewById(R.id.activity_major_sort);
        this.listGroup = new ArrayList<>();
        this.listItem = new HashMap<>();
        this.adaptor = new MainAdaptor(this,listGroup,listItem);
        expandableListView.setAdapter(adaptor);
        getListViewData();

    }

    //Get listView
    public void getListViewData() {
        //Get major data
        University[] sorted = getProgramBasedOnCategory(major,"admission_average");

        //Loop through this and make hashmap
        int count = 1;
        for (University u : sorted) {
            //Get University name
            String universityName = this.universityNameConversion.get(u.getName());
            listGroup.add(count+") "+universityName);

            //Get program Info
            Program p = u.getProgram(this.major);
            List<String> programInfo = new ArrayList<>();
            programInfo.add("Program name: \n" + p.getName());
            programInfo.add("Admission Average: \n" + p.getAdmission_average()+"%");
            programInfo.add("Domestic Tuition: \n$" + p.getLocal_tuition());
            programInfo.add("International Tuition: \n$" + p.getInternational_tuition());
            programInfo.add("Requirements: \n" + removeQuotations(p.getRequirements()));
            programInfo.add("Coop: \n" + booleanToString(p.isCoop()));
            programInfo.add("Target Enrolment: \n" + p.getTarget_enrolment());
            programInfo.add("Supplementary Application: \n" + booleanToString(p.isSupplementary_applicatoin()));
            listItem.put(count+") "+universityName,programInfo);
            count++;
        }

        //test listgroup
        for (String s : listGroup) {
            Log.i("myapp",s);
        }

        this.adaptor.notifyDataSetChanged();
    }


    //Function to remove quotations (for requirement)
    public String removeQuotations(String s) {
        if (s.charAt(0) == '"') {
            return s.substring(1, s.length() - 1);
        } else return s;
    }


    //get data
    public void getData() {
        getUniversityData();
        getUniversityRanking();
    }


    //Get University data from CSV
    public void getUniversityData() {
        for (String name : this.universityFileNames) {
            InputStream ins = getResources().openRawResource(getResources().getIdentifier(name,"raw",getPackageName()));
            Scanner scanner = new Scanner(ins);
            String header = scanner.nextLine(); //get rid of header
            //retrieve data
            University university = new University(name);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] cells = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                Program p = new Program(cells[0],                       //name
                        Integer.parseInt(cells[1]),                       //Admission Average
                        Integer.parseInt(cells[2]),     //local tuition
                        Integer.parseInt(cells[3]),     //international tuition
                        cells[4],                       //requirement
                        yesNoConversion(cells[5]),      //coop
                        cells[6],                       //target enrolment
                        yesNoConversion(cells[7]));     //supplementary application
                university.addProgram(p);
            }
            universities.add(university);
        }

    }

    //Function that changes Yes/No to boolean
    public boolean yesNoConversion(String s) {
        switch (s) {
            case "Yes":
                return true;
            case "No":
                return false;
            default: // ?
                return false;
        }
    }

    public String booleanToString(boolean b) {
        if (b) return "Yes";
        else return "No";
    }

    public void getUniversityRanking() {
        InputStream ins = getResources().openRawResource(getResources().getIdentifier("qs_world_ranking", "raw", getPackageName()));
        Scanner scanner = new Scanner(ins);
        scanner.nextLine();
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(",");
            rankingList.put(line[0], line[1]);
        }
        scanner.close();
        Set<String> uni = rankingList.keySet();
        for (String u : universityFileNames) {
            for (String U : uni) {
                if (U.toLowerCase().contains(u)) {
                    String x = rankingList.get(U);
                    if (!x.equals("N/A")) {
                        int y = Integer.parseInt(x);
                        for (University z : universities)
                            if (z.getName().equals(u))
                                z.setRanking(y);
                    }
                }
            }
        }
    }

    public void booleanFilter(String programName, String category) {
        for (University u : universities) {
            for (Program p : u.getPrograms()) {
                if (p.getName().contains(programName)) {
                    if (category.equals("coop")) {
                        if (p.isCoop()) {
                            if (!boolList.contains(u.getName()))
                                boolList.add(u.getName());
                        }
                    }
                    if (category.equals("supplementary_application")) {
                        if (p.isSupplementary_applicatoin()) {
                            if (!boolList.contains(u.getName()))
                                boolList.add(u.getName());
                        }
                    }
                }
            }
        }
    }

    //Sort universities based on: admission average/tuition
    public University[] getProgramBasedOnCategory(String programName, String category) {
        HashMap<String,Integer> programRanking = new HashMap<String,Integer>();
        for (University u : this.universities) {
            Program p = u.getProgram(programName);
            if (p != null) {
                int val = 0;
                switch (category) {
                    case "admission_average":
                        val = p.getAdmission_average();
                        break;
                    case "ranking":
                        break;
                }
                programRanking.put(u.getName(),new Integer(val));
            }

        }
        //make hashmap into array [ names ]    [ val ]   at same index
        String[] universityNames = objToString(programRanking.keySet().toArray());
        Integer[] valuesInteger = objToInt(programRanking.values().toArray());
        //change this to int[]
        int[] values = new int[valuesInteger.length];
        for (int i = 0; i < valuesInteger.length; i++) {
            values[i] = Integer.parseInt(valuesInteger[i].toString());
        }
        //sort values and use that index to sort the names
        String[] resultInString = sortDecreasingOrder(universityNames, values);
        //Change this into array of universities
        University[] result = new University[resultInString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = getUniversity(resultInString[i]);
        }
        return result;
    }

    public String[] objToString(Object[] obj) {
        String[] result = new String[obj.length];
        for (int i = 0; i < obj.length; i++) {
            result[i] = obj[i].toString();
        } return result;
    }

    public Integer[] objToInt(Object[] obj) {
        Integer[] result = new Integer[obj.length];
        for (int i = 0; i < obj.length; i++) {
            result[i] = Integer.parseInt(obj[i].toString());
        } return result;
    }

    //bubble sort
    public String[] sortDecreasingOrder(String[] universityNames, int[] values) {
        boolean sorted = true;
        while (sorted) {
            sorted = false;
            for (int i = 0; i < values.length - 1; i++) {
                if (values[i+1] > values[i]) {
                    exchInt(values,i,i+1);
                    exchString(universityNames,i,i+1);
                    sorted = true;
                }
            }
        } return universityNames;
    }

    public void exchString(String[] a, int i, int j) {
        String m1 = a[i];
        a[i] = a[j];
        a[j] = m1;
    }

    public void exchInt(int[] a, int i, int j) {
        int m1 = a[i];
        a[i] = a[j];
        a[j] = m1;
    }

    //Get university given string name
    public University getUniversity(String name) {
        for (University u : this.universities) {
            if (u.getName().equals(name)) return u;
        } return null;
    }


}
