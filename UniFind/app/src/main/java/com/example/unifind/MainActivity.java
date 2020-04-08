package com.example.unifind;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import java.io.InputStream;
import java.util.Scanner;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    public String[] universityFileNames;
    public ArrayList<University> universities;
    private HashMap<String,String> rankingList;
    ArrayList<Program> filterList;

    private final String[] categories = new String[] {"admission_average",              // 0
                                                      "local_tuition",                  // 1
                                                      "international_tuition",          // 2
                                                      "coop",                           // 3
                                                      "target_enrolment",               // 4
                                                      "supplementary_application"};     // 5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.universityFileNames = new String[] {"algoma", "brock", "carleton",
                                                 "guelph", "hearst", "lakehead",
                                                "laurentian", "mcmaster", "nipissing",
                                                 "ocad", "uoit", "ottawa",
                                                 "queens", "ryerson", "trent",
                                                "uoft", "waterloo", "western",
                                                "wilfred_laurier", "windsor", "york"};



        this.universities = new ArrayList<University>();
        this.filterList = new ArrayList<>();
        this.rankingList = new HashMap<String,String>();

        Button b =  (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();

                Log.i("myapp","sort computer science on admission average-----------------------------------------------------");
                University[] sortResult = getProgramBasedOnCategory("Computer Science","admission_average");
                for (University s : sortResult) {
                    Log.i("sort",s.getName());
                }


                Log.i("myapp","carleton---------------------------------------------------------------------------------------");
                coopOrSupFilter("carleton");
                for (Program p : filterList) {
                    Log.i("myapp",p.getName());
                }

            }
        });
    }

    //get data
    public void getData() {
        getUniversityData();
        getUniversityRanking();
    }

    //Get University data from CSV
    public void getUniversityData() {
        final String dir = System.getProperty("user.dir");
        for (String name : this.universityFileNames) {
            InputStream ins = getResources().openRawResource(getResources().getIdentifier(name,"raw",getPackageName()));
            Scanner scanner = new Scanner(ins);
            String header = scanner.nextLine(); //get rid of header
            TextView text = (TextView) findViewById(R.id.text);
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

    public void coopOrSupFilter(String universityName) {
        for (University u : universities) {
            if (universityName.equals(u.getName())) {
                for (Program p : u.getPrograms()) {
                    if (p.isCoop() == true) {
                        filterList.add(p);
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
        String[] resultInString = sortIncreasingOrder(universityNames, values);
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
    public String[] sortIncreasingOrder(String[] universityNames, int[] values) {
        boolean sorted = true;
        while (sorted) {
            sorted = false;
            for (int i = 0; i < values.length - 1; i++) {
                if (values[i+1] < values[i]) {
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
