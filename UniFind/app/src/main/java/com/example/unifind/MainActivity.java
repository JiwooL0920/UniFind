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


public class MainActivity extends AppCompatActivity {

    private String[] universityFileNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.universityFileNames = new String[] {"algoma",
                                                "brock",
                                                "carleton",
                                                "guelph",
                                                "hearst",
                                                "lakehead",
                                                "laurentian",
                                                "mcmaster",
                                                "nipissing",
                                                "ocad",
                                                "uoit",
                                                "ottawa",
                                                "queens",
                                                "ryerson",
                                                "trent",
                                                "uoft",
                                                "waterloo",
                                                "western",
                                                "wilfred_laurier",
                                                "windsor",
                                                "york"};

        Button b =  (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUniversityData();

            }
        });
    }

    //Get University data from CSV
    public void getUniversityData() {
        Log.i("myapp","reached2");
            final String dir = System.getProperty("user.dir");
            for (String name : this.universityFileNames) {
                InputStream ins = getResources().openRawResource(getResources().getIdentifier(name,"raw",getPackageName()));
                Scanner scanner = new Scanner(ins);
                String s = scanner.nextLine();
                TextView text = (TextView) findViewById(R.id.text);
                Log.i("myapp",name);
                text.setText(s);
                //retrieve data
            }

    }

    //Get ranking data 

}
