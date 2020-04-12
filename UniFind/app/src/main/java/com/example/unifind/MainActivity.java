package com.example.unifind;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<String> webs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajor();
            }
        });
        getWebList();
        for (int i = 0; i< buttons.size(); i++) {
            Button temp = buttons.get(i);
            final int finalI = i;
            temp.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(webs.get(finalI)));
                    startActivity(intent);

                }
            });
        }
    }

    /* Method openMajor is used to open an activity. In activity_main.xml, when button "Search By Major" is clicked,
     * this method is called to open activity_major.
     */
    public void openMajor() {
        Intent intent = new Intent(this, MajorActivity.class);
        startActivity(intent);
    }




    private void getButtonList(){
        buttons.add((Button) findViewById(R.id.imagebuttonAlgoma));
        buttons.add((Button) findViewById(R.id.imagebuttonBrock));
        buttons.add((Button) findViewById(R.id.imagebuttonCarleton));
        buttons.add((Button) findViewById(R.id.imagebuttonGuelph));
        buttons.add((Button) findViewById(R.id.imagebuttonHearst));
        buttons.add((Button) findViewById(R.id.imagebuttonLakehead));
        buttons.add((Button) findViewById(R.id.imagebuttonLaurentian));
        buttons.add((Button) findViewById(R.id.imagebuttonMcMaster));
        buttons.add((Button) findViewById(R.id.imagebuttonOCAD));
        buttons.add((Button) findViewById(R.id.imagebuttonOnTech));
        buttons.add((Button) findViewById(R.id.imagebuttonOttawa));
        buttons.add((Button) findViewById(R.id.imagebuttonQueens));
        buttons.add((Button) findViewById(R.id.imagebuttonRyerson));
        buttons.add((Button) findViewById(R.id.imagebuttonToronto));
        buttons.add((Button) findViewById(R.id.imagebuttonTrent));
        buttons.add((Button) findViewById(R.id.imagebuttonWaterloo));
        buttons.add((Button) findViewById(R.id.imagebuttonWestern));
        buttons.add((Button) findViewById(R.id.imagebuttonWilfridLaurier));
        buttons.add((Button) findViewById(R.id.imagebuttonWindsor));
        buttons.add((Button) findViewById(R.id.imagebuttonYork));
    }
    private void getWebList(){
        getButtonList();
        webs.add("https://www.algomau.ca/admissions/");
        webs.add("https://brocku.ca/admissions/");
        webs.add("https://admissions.carleton.ca/");
        webs.add("https://admission.uoguelph.ca/");
        webs.add("http://www.uhearst.ca/admission");
        webs.add("https://www.lakeheadu.ca/admissions");
        webs.add("https://laurentian.ca/admissions");
        webs.add("https://www.mcmaster.ca/");
        webs.add("https://www.ocadu.ca/admissions");
        webs.add("https://ontariotechu.ca/future-students/index.php");
        webs.add("https://www.uottawa.ca/undergraduate-admissions/");
        webs.add("https://www.queensu.ca/admission/about-applying/requirements");
        webs.add("https://www.ryerson.ca/admissions/");
        webs.add("https://future.utoronto.ca/apply/");
        webs.add("https://www.trentu.ca/futurestudents/undergraduate/admission-requirements");
        webs.add("https://uwaterloo.ca/admissions/");
        webs.add("https://welcome.uwo.ca/admissions/");
        webs.add("https://www.wlu.ca/future-students/undergraduate/admissions/index.html");
        webs.add("http://learn.uwindsor.ca/future-students");
        webs.add("https://futurestudents.yorku.ca/requirements");
    }

}