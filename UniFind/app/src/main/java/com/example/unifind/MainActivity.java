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
        this.universityFileNames = new String[] {"Algoma.csv",
                                                "Brock.csv",
                                                "Carleton.csv",
                                                "Guelph,csv",
                                                "Hearst.csv",
                                                "Lakehead.csv",
                                                "Laurentian.csv",
                                                "McMaster",
                                                "Nipissing.csv",
                                                "OCAD.csv",
                                                "OnTechU.csv",
                                                "Ottawa.csv",
                                                "Queens.csv",
                                                "Ryerson.csv",
                                                "Trent.csv",
                                                "UofT.csv",
                                                "Waterloo.csv",
                                                "Western.csv",
                                                "Wilfred_Laurier.csv",
                                                "Windsor.csv",
                                                "York.csv"};

        Button b =  (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myapp","reached1");
                getUniversityData();

            }
        });
    }

    //Get University data from CSV
    public void getUniversityData() {
        Log.i("myapp","reached2");
            final String dir = System.getProperty("user.dir");
            InputStream ins = getResources().openRawResource(getResources().getIdentifier("mcmaster","raw",getPackageName()));
            Scanner scanner = new Scanner(ins);
            String s = scanner.nextLine();
            TextView text = (TextView) findViewById(R.id.text);
            text.setText(s);
    }

}
