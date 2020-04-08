package com.example.unifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MajorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        Button b =  (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSort();
            }
        });

    }

    public void openMajorSort() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Computer Science");
        startActivity(intent);
    }
}
