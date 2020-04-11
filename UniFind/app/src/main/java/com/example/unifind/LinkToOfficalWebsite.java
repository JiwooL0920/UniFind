package com.example.unifind;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unifind.R;

public class LinkToOfficalWebsite extends AppCompatActivity {

    private TextView algoma;
    //    @Override
    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button algoma = findViewById(R.id.imagebuttonAlgoma);
        algoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.algomau.ca/"));
                startActivity(browseIntent);
            }


        });
    }



}