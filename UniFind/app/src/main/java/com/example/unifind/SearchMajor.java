package com.example.unifind;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchMajor extends AppCompatActivity {
    private ArrayList<Integer> BUTTONS=new ArrayList<>();
    ScrollView scrollView =findViewById(R.id.scrollView1);



    public void getButtonList(){
        int buttonID=R.id.button;

        for (int i = 0; i < 50; i++)
        {
            int buttonNum=Integer.parseInt(Integer.toString(buttonID) + Integer.toString(i));
            BUTTONS.add(R.id.button);
            BUTTONS.add(buttonNum);
        }
    }
    private String sortCategory;


    //Expandable view
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listItem;
    MainAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        this.sortCategory = "A";
        final Spinner letterSpinner = findViewById(R.id.firstLetterSpinner);

        ArrayAdapter aA = ArrayAdapter.createFromResource(this, R.array.firstLetter, android.R.layout.simple_spinner_item);
        aA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterSpinner.setAdapter(aA);



        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sortCategoryRaw = letterSpinner.getSelectedItem().toString();
                switch (sortCategoryRaw) {
                    case "A":
                        sortCategory = "A";
                        break;
                    case "B":
                        sortCategory = "B";
                        break;
                    case "C":
                        sortCategory = "C";
                        break;
                }
                resetSetting(sortCategory);

            }
        });
    }

    public void resetSetting(String sortCategory) {
        getButtonList();

        this.sortCategory = sortCategory;
        for (int i : BUTTONS) {
            Button btn= findViewById(i);
            char t= btn.getText().charAt(0);
            if (t==sortCategory.charAt(0)) {
                btn.setVisibility(View.VISIBLE);
            }
        }
    }



}
