package com.example.unifind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MajorActivity extends AppCompatActivity {

    private ArrayList<Integer> buttonid=new ArrayList<>();

    //get the list of button id
    public void getButtonList(){
        int buttonID=R.id.button;

        String b="button";
        for (int i = 1; i < 51; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            buttonid.add(id);
        }
    }

    //refresh function for changing letters
    public void refresh(String sortLetter) {
        getButtonList();

        //loop through the letters in buttonid arraylist
        for (int i : buttonid) {
            Button btn = findViewById(i);
            String s=(String) btn.getText().toString();
            //if choose all make every major visible
            if (sortLetter=="*"){
                btn.setVisibility(View.VISIBLE);
            }
            //if the first letter not the same set visibility to gone
            else if (s.charAt(0)!=(sortLetter.charAt(0))) {
                btn.setVisibility(View.GONE);
            } else {
                btn.setVisibility(View.VISIBLE);
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        //get the spinner by id
        final Spinner letterSpinner = findViewById(R.id.firstLetterSpinner);

        //set the drop down of the spinner to the firstLetter
        ArrayAdapter aA = ArrayAdapter.createFromResource(this, R.array.firstLetter, android.R.layout.simple_spinner_item);
        aA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterSpinner.setAdapter(aA);


        Button refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sortLetter = letterSpinner.getSelectedItem().toString();

                //if select ALL, make the sortLetter as *
                switch (sortLetter) {
                    case "ALL":
                        sortLetter = "*";
                        break;
                }
                refresh(sortLetter);
            }
        });


        /**
         * Below there are 50 buttons that represent 50 majors available in more than one university.
         * They are displayed in alphabet order. For each button, it has a corresponding openMajorSort
         * method to open the sorting page. When a button is clicked, the text of that button (program name)
         * will be transferred to the sorting page as an input. And the content of major_sorting will be
         * filtered and sorted based on that.
         */

        Button b1 =  (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb1();
            }
        });

        Button b2 =  (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb2();
            }
        });

        Button b3 =  (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb3();
            }
        });

        Button b4 =  (Button) findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb4();
            }
        });

        Button b5 =  (Button) findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb5();
            }
        });

        Button b6 =  (Button) findViewById(R.id.button6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb6();
            }
        });

        Button b7 =  (Button) findViewById(R.id.button7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb7();
            }
        });

        Button b8 =  (Button) findViewById(R.id.button8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb8();
            }
        });

        Button b9 =  (Button) findViewById(R.id.button9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb9();
            }
        });

        Button b10 =  (Button) findViewById(R.id.button10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb10();
            }
        });

        Button b11 =  (Button) findViewById(R.id.button11);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb11();
            }
        });

        Button b12 =  (Button) findViewById(R.id.button12);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb12();
            }
        });

        Button b13 =  (Button) findViewById(R.id.button13);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb13();
            }
        });

        Button b14 =  (Button) findViewById(R.id.button14);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb14();
            }
        });

        Button b15 =  (Button) findViewById(R.id.button15);
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb15();
            }
        });

        Button b16 =  (Button) findViewById(R.id.button16);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb16();
            }
        });

        Button b17 =  (Button) findViewById(R.id.button17);
        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb17();
            }
        });

        Button b18 =  (Button) findViewById(R.id.button18);
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb18();
            }
        });

        Button b19 =  (Button) findViewById(R.id.button19);
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb19();
            }
        });

        Button b20 =  (Button) findViewById(R.id.button20);
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb20();
            }
        });

        Button b21 =  (Button) findViewById(R.id.button21);
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb21();
            }
        });

        Button b22 =  (Button) findViewById(R.id.button22);
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb22();
            }
        });

        Button b23 =  (Button) findViewById(R.id.button23);
        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb23();
            }
        });

        Button b24 =  (Button) findViewById(R.id.button24);
        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb24();
            }
        });

        Button b25 =  (Button) findViewById(R.id.button25);
        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb25();
            }
        });

        Button b26 =  (Button) findViewById(R.id.button26);
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb26();
            }
        });

        Button b27 =  (Button) findViewById(R.id.button27);
        b27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb27();
            }
        });

        Button b28 =  (Button) findViewById(R.id.button28);
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb28();
            }
        });

        Button b29 =  (Button) findViewById(R.id.button29);
        b29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb29();
            }
        });

        Button b30 =  (Button) findViewById(R.id.button30);
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb30();
            }
        });

        Button b31 =  (Button) findViewById(R.id.button31);
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb31();
            }
        });

        Button b32 =  (Button) findViewById(R.id.button32);
        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb32();
            }
        });

        Button b33 =  (Button) findViewById(R.id.button33);
        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb33();
            }
        });

        Button b34 =  (Button) findViewById(R.id.button34);
        b34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb34();
            }
        });

        Button b35 =  (Button) findViewById(R.id.button35);
        b35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb35();
            }
        });

        Button b36 =  (Button) findViewById(R.id.button36);
        b36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb36();
            }
        });

        Button b37 =  (Button) findViewById(R.id.button37);
        b37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb37();
            }
        });

        Button b38 =  (Button) findViewById(R.id.button38);
        b38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb38();
            }
        });

        Button b39 =  (Button) findViewById(R.id.button39);
        b39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb39();
            }
        });

        Button b40 =  (Button) findViewById(R.id.button40);
        b40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb40();
            }
        });

        Button b41 =  (Button) findViewById(R.id.button41);
        b41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb41();
            }
        });

        Button b42 =  (Button) findViewById(R.id.button42);
        b42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb42();
            }
        });

        Button b43 =  (Button) findViewById(R.id.button43);
        b43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb43();
            }
        });

        Button b44 =  (Button) findViewById(R.id.button44);
        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb44();
            }
        });

        Button b45 =  (Button) findViewById(R.id.button45);
        b45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb45();
            }
        });

        Button b46 =  (Button) findViewById(R.id.button46);
        b46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb46();
            }
        });

        Button b47 =  (Button) findViewById(R.id.button47);
        b47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb47();
            }
        });

        Button b48 =  (Button) findViewById(R.id.button48);
        b48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb48();
            }
        });

        Button b49 =  (Button) findViewById(R.id.button49);
        b49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb49();
            }
        });

        Button b50 =  (Button) findViewById(R.id.button50);
        b50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMajorSortb50();
            }
        });

    }

    public void openMajorSortb1() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Anthropology");
        startActivity(intent);
    }

    public void openMajorSortb2() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Architectural");
        startActivity(intent);
    }

    public void openMajorSortb3() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Art");
        startActivity(intent);
    }

    public void openMajorSortb4() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Biochemistry");
        startActivity(intent);
    }

    public void openMajorSortb5() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Biology");
        startActivity(intent);
    }

    public void openMajorSortb6() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Biomedical");
        startActivity(intent);
    }

    public void openMajorSortb7() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Biotechnology");
        startActivity(intent);
    }

    public void openMajorSortb8() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Business");
        startActivity(intent);
    }

    public void openMajorSortb9() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Chemistry");
        startActivity(intent);
    }

    public void openMajorSortb10() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Civil");
        startActivity(intent);
    }

    public void openMajorSortb11() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Commerce");
        startActivity(intent);
    }

    public void openMajorSortb12() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Communication");
        startActivity(intent);
    }

    public void openMajorSortb13() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Community");
        startActivity(intent);
    }

    public void openMajorSortb14() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Computer Science");
        startActivity(intent);
    }

    public void openMajorSortb15() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Criminology");
        startActivity(intent);
    }

    public void openMajorSortb16() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Data Science");
        startActivity(intent);
    }

    public void openMajorSortb17() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Digital");
        startActivity(intent);
    }

    public void openMajorSortb18() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Earth Science");
        startActivity(intent);
    }

    public void openMajorSortb19() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Economics");
        startActivity(intent);
    }

    public void openMajorSortb20() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Education");
        startActivity(intent);
    }

    public void openMajorSortb21() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Electrical");
        startActivity(intent);
    }

    public void openMajorSortb22() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Engineering");
        startActivity(intent);
    }

    public void openMajorSortb23() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","English");
        startActivity(intent);
    }

    public void openMajorSortb24() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Environment");
        startActivity(intent);
    }

    public void openMajorSortb25() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Finance");
        startActivity(intent);
    }

    public void openMajorSortb26() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Geography");
        startActivity(intent);
    }

    public void openMajorSortb27() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Health Science");
        startActivity(intent);
    }

    public void openMajorSortb28() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","History");
        startActivity(intent);
    }

    public void openMajorSortb29() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Humanities");
        startActivity(intent);
    }

    public void openMajorSortb30() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Information");
        startActivity(intent);
    }

    public void openMajorSortb31() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","International");
        startActivity(intent);
    }

    public void openMajorSortb32() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Kinesiology");
        startActivity(intent);
    }

    public void openMajorSortb33() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Law");
        startActivity(intent);
    }

    public void openMajorSortb34() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Life Science");
        startActivity(intent);
    }

    public void openMajorSortb35() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Management");
        startActivity(intent);
    }

    public void openMajorSortb36() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Math");
        startActivity(intent);
    }

    public void openMajorSortb37() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Mechanical Engineering");
        startActivity(intent);
    }

    public void openMajorSortb38() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Media");
        startActivity(intent);
    }

    public void openMajorSortb39() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Medical");
        startActivity(intent);
    }

    public void openMajorSortb40() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Music");
        startActivity(intent);
    }

    public void openMajorSortb41() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Nursing");
        startActivity(intent);
    }

    public void openMajorSortb42() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Philosophy");
        startActivity(intent);
    }

    public void openMajorSortb43() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Physics");
        startActivity(intent);
    }

    public void openMajorSortb44() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Political");
        startActivity(intent);
    }

    public void openMajorSortb45() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Psychology");
        startActivity(intent);
    }

    public void openMajorSortb46() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Science");
        startActivity(intent);
    }

    public void openMajorSortb47() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Social Science");
        startActivity(intent);
    }

    public void openMajorSortb48() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Sociology");
        startActivity(intent);
    }

    public void openMajorSortb49() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Visual Arts");
        startActivity(intent);
    }

    public void openMajorSortb50() {
        Intent intent = new Intent(this, MajorSortActivity.class);
        intent.putExtra("Major","Women’s Studies");
        startActivity(intent);
    }
}
