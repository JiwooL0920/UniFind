package com.example.unifind;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

import android.util.Log;

import android.widget.Button;
import android.widget.Spinner;

public class MajorSortActivity extends AppCompatActivity{
    private String major; // selected major
    private int tuitionUpperBound;
    private boolean coop;
    private boolean isInternational;
    private String sortCategory;
    private Model model;

    //Expandable view
    ExpandableListView expandableListView;
    List<String> listGroup; // list of order + university name
    HashMap<String,List<String>> listItem; // hashmap university name and detail info
    MainAdaptor adaptor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_sort);

        // set the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("UniFind");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);

        this.tuitionUpperBound = Integer.MAX_VALUE; //initialize to nothing
        this.coop = false;
        this.isInternational = false;
        this.sortCategory = "ranking";
        this.model = new Model();

        //Get Data
        getData();

        //Retrieved data (what major) passed from MajorActivity
        this.major = getIntent().getStringExtra("Major");
        TextView majorNameTextView = findViewById(R.id.majorName);
        majorNameTextView.setText(this.major);

        //Expandable View
        expandableListView = findViewById(R.id.activity_major_sort);
        this.listGroup = new ArrayList<>();
        this.listItem = new HashMap<>();
        this.adaptor = new MainAdaptor(this,listGroup,listItem);
        expandableListView.setAdapter(adaptor);

        // get ListView
        getListViewData();

        //Search/Sort setting
        final EditText et = findViewById(R.id.tuitionTF);
        final Switch coopSwitch = findViewById(R.id.coopSwitch);
        final Switch internationalSwitch = findViewById(R.id.internationalSwitch);
        final Spinner rankSpinner = findViewById(R.id.rankSpinner);

        ArrayAdapter aa = ArrayAdapter.createFromResource(this,R.array.rankingOptions,android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rankSpinner.setAdapter(aa);

        //Refresh Button
        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFieldString = et.getText().toString();
                if (model.isNumeric(textFieldString) || textFieldString.equals("")) {
                    int textFieldInt;
                    if (textFieldString.equals("")) {
                        textFieldInt = Integer.MAX_VALUE; // initialize to nothing
                    } else {
                        textFieldInt = Integer.parseInt(textFieldString); // change type string to int
                    }
                    boolean coopSwitchStatus = coopSwitch.isChecked(); // check coop on/off
                    boolean internationalStatus = internationalSwitch.isChecked(); // check 105 on/off
                    String sortCategoryRaw = rankSpinner.getSelectedItem().toString(); // get the catetory selected in rank Spinner

                    Log.i("check","raw"+sortCategoryRaw); // pass

                    // 4 categories
                    String sortCategory = "admission_average";
                    switch (sortCategoryRaw) {
                        case "Ranking":
                            sortCategory = "ranking";
                            break;
                        case "Admission Average":
                            sortCategory = "admission_average";
                            break;
                        case "Tuition":
                            sortCategory = "tuition";
                            break;
                    }
                    //reset
                    resetSetting(coopSwitchStatus,internationalStatus,textFieldInt,sortCategory);
                }
            }
        });

    }

    public void resetSetting(boolean coop, boolean internationalStatus, int textFieldInput, String sortCategory) {

        this.tuitionUpperBound = textFieldInput;
        this.coop = coop;
        this.isInternational = internationalStatus;
        this.sortCategory = sortCategory;
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
        String category = this.sortCategory;
        if (this.sortCategory.equals("tuition")) {
            if (!this.isInternational) category = "local_tuition";
            else category = "international_tuition";
        }
        University[] sorted = model.getProgramBasedOnCategory(major,category,this.isInternational,this.coop,this.tuitionUpperBound);

        //Loop through this and make hashmap
        int count = 1;
        for (University u : sorted) {
            //Get University name
            String universityName = model.getUniversityNameConversion().get(u.getName());
            listGroup.add(count+") "+universityName);

            //Get program Info
            Program p = u.getProgram(this.major);
            List<String> programInfo = new ArrayList<>();
            programInfo.add("Program name: \n" + p.getName());
            if (u.getRanking() == Integer.MAX_VALUE) {
                programInfo.add("University ranking: \n" + "N/A");
            } else {
                programInfo.add("University ranking: \n" + u.getRanking());
            }
            programInfo.add("Admission Average: \n" + p.getAdmission_average()+"%");
            programInfo.add("Domestic Tuition: \n$" + p.getLocal_tuition());
            programInfo.add("International Tuition: \n$" + p.getInternational_tuition());
            programInfo.add("Requirements: \n" + model.removeQuotations(p.getRequirements()));
            programInfo.add("Coop: \n" + model.booleanToString(p.isCoop()));
            programInfo.add("Target Enrolment: \n" + p.getTarget_enrolment());
            programInfo.add("Supplementary Application: \n" + model.booleanToString(p.isSupplementary_applicatoin()));
            listItem.put(count+") "+universityName,programInfo);
            count++;
        }

        this.adaptor.notifyDataSetChanged();
    }

    //get data
    public void getData() {
        getUniversityData();
        getUniversityRanking();
    }

    //Get University data from CSV
    public void getUniversityData() {
        for (String name : model.getUniversityFileNames()) {
            InputStream ins = getResources().openRawResource(getResources().getIdentifier(name,"raw",getPackageName()));
            Scanner scanner = new Scanner(ins);
            String header = scanner.nextLine(); //get rid of header
            //retrieve data
            University university = new University(name);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] cells = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); // split by comma outside of quotation marks
                Program p = new Program(cells[0],                       //name
                        Integer.parseInt(cells[1]),                       //Admission Average
                        Integer.parseInt(cells[2]),     //local tuition
                        Integer.parseInt(cells[3]),     //international tuition
                        cells[4],                       //requirement
                        model.yesNoConversion(cells[5]),      //coop
                        cells[6],                       //target enrolment
                        model.yesNoConversion(cells[7]));     //supplementary application
                university.addProgram(p);
            }
            model.addUniversity(university);
        }

    }


    public void getUniversityRanking() {
        InputStream ins = getResources().openRawResource(getResources().getIdentifier("qs_world_ranking", "raw", getPackageName()));
        Scanner scanner = new Scanner(ins);
        scanner.nextLine(); // skip first line
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(","); // split by comma
            model.addRankingList(line[0], line[1]); // hashmap university name and qs ranking
        }
        scanner.close();
        Set<String> uni = model.getRankingList().keySet(); // get all the key(formal university name)
        for (String u : model.getUniversityFileNames()) {
            for (String U : uni) {
                if (U.toLowerCase().contains(u)) {
                    String x = model.getRankingList().get(U);
                    if (!x.equals("N/A")) {
                        int y = Integer.parseInt(x); // change type to int
                        for (University z : model.getUniversities())
                            if (z.getName().equals(u))
                                z.setRanking(y); // set ranking
                    }
                }
            }
        }
    }
}
