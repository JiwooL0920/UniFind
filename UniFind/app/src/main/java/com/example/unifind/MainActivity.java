package com.example.unifind;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    HashMap<String,String> universityNameConversion;
    private HashMap<String,String> ranking;


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

        //This one works??? this doesnt work yyyyy?
        this.universityNameConversion = new HashMap<String,String>();
        this.universityNameConversion.put("algoma","Algoma University");
        this.universityNameConversion.put("brock","Brock University");
        this.universityNameConversion.put("carleton","Carleton University");
        this.universityNameConversion.put("guelph","University of Guelph");
        this.universityNameConversion.put("hearst","Université de Hearst");
        this.universityNameConversion.put("leakehead","Lakehead University");
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
//        this.universityNameConversion.put("wilfred_laurier","Wilfred Laurier University");
        this.universityNameConversion.put("winsor","University of Windsor");
        this.universityNameConversion.put("york","York University");

        this.universities = new ArrayList<University>();

        Button b =  (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
                for ()
                Log.i("myapp",ranking);
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
//        Log.i("myapp","reached2");
        final String dir = System.getProperty("user.dir");
        for (String name : this.universityFileNames) {
            InputStream ins = getResources().openRawResource(getResources().getIdentifier(name,"raw",getPackageName()));
            Scanner scanner = new Scanner(ins);
            String header = scanner.nextLine(); //get rid of header
            TextView text = (TextView) findViewById(R.id.text);
//                Log.i("myapp",name);
            //retrieve data
            University university = new University(name);
//                Log.i("myapp",name);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] cells = s.split(",");
//                    Log.i("myapp",cells[0]);
                Program p = new Program(cells[0],                       //name
                        cells[1],                       //Admission Average
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
        //check in log
        Log.i("myapp","finalresult====================================");
        for (University u : universities) {
            Log.i("myapp",u.getName());
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

    //TODO [Jennie]: update university's ranking field
    public void getUniversityRanking() {
        InputStream ins = getResources().openRawResource(getResources().getIdentifier("qs_world_ranking", "raw", getPackageName()));
        Scanner scanner = new Scanner(ins);
        scanner.nextLine();
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(",");
            ranking.put(line[0], line[1]);
        }
        scanner.close();
        Set<String> uni = ranking.keySet();
        for (String u : universityFileNames) {
            for (String U : uni) {
                if (U.toLowerCase().contains(u)) {
                    String x = ranking.get(U);
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
    //Get ranking data


}
