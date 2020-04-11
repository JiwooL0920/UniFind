package com.example.unifind;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;


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
    }

    /* Method openMajor is used to open an activity. In activity_main.xml, when button "Search By Major" is clicked,
     * this method is called to open activity_major.
     */
    public void openMajor() {
        Intent intent = new Intent(this, MajorActivity.class);
        startActivity(intent);
    }

}
