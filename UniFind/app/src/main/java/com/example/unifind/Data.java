package com.example.unifind;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.InputStream;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Data {
    private ArrayList<University> universities;
    private String[] universityFileNames;

    public Data() throws FileNotFoundException, IOException {
        this.universities = new ArrayList<University>();
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
    }

    //Get University data from CSV
    public void getUniversityData() throws FileNotFoundException, IOException {
        try {
            for (String universityName : universityFileNames) {
                final String dir = System.getProperty("user.dir");
                InputStream ins = getResources().openRawResource(getResources().getIdentifier(universityName,"raw",getPackageName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Get Ranking data from CSV

    //Getter for university
//    public ArrayList<University> getUniversities() {
//
//    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Data d = new Data();
        d.getUniversityData();
    }
}
