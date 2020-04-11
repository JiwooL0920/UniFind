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
    private ArrayList<Integer> BUTTONS = new ArrayList<>();
    ScrollView scrollView = findViewById(R.id.scrollView1);


    public void getButtonList() {
        int buttonID = R.id.button;

        for (int i = 0; i < 50; i++) {
            int buttonNum = Integer.parseInt(Integer.toString(buttonID) + Integer.toString(i));
            BUTTONS.add(R.id.button);
            BUTTONS.add(buttonNum);
        }
    }

    private String sortCategory;


    //Expandable view
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String, List<String>> listItem;
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
    }
}