package com.example.petrre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayActivity extends AppCompatActivity {
    public static final String PET_NAME = "Pet Name";
    public static final String BREED_NAME = "Breed Name";
    public static final String AGE_CATEGORY = "Age Category";
    public static final String VACCINE = "Vaccine";
    public static final String LAST_DATE = "Last Date";
    public static final String HEIGHT = "Height";
    public static final String WEIGHT = "Weight";
    DBHelper mydb;
    EditText ed1,ed2, ed3, ed4, ed5, ed6, ed7;
    Button b,b1,b2,b3,b4;
    CheckBox c1,c2,c3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mydb = new DBHelper(this);
        mydb.truncateUsers();

        b = (Button) findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);
        c1 = (CheckBox) findViewById(R.id.ch1);
        c2 = (CheckBox) findViewById(R.id.ch2);
        c3 = (CheckBox) findViewById(R.id.ch3);
        b4 = (Button) findViewById(R.id.button4);
        ed4 = (EditText)findViewById(R.id.editText4);
        ed5 = (EditText)findViewById(R.id.editText5);
        ed6 = (EditText)findViewById(R.id.editText6);
        ed7 = (EditText)findViewById(R.id.editText7);

        
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.textView4);
        //textView.setText(message);




        final Intent intent = new Intent(this, ReminderActivity.class);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        /*Date date = null;
                        try {
                            date =new SimpleDateFormat("dd/MM/yyyy").parse( ed7.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }*/
                AnimalTable animalTable = new AnimalTable(ed2.getText().toString(), ed6.getText().toString(), ed7.getText().toString());
                mydb.truncateAnimals();
                mydb.insertAnimal(animalTable.breed_name, animalTable.vaccine, animalTable.last_date);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check = true;
                if((c1.isChecked()) && (c2.isChecked()) && c3.isChecked()){
                    check = false;
                }else if((c1.isChecked()) && (c2.isChecked())){
                    check = false;
                }else if((c2.isChecked()) && (c3.isChecked())){
                    check = false;
                }else if((c1.isChecked()) && (c3.isChecked())){
                    check = false;
                }





                if(check) {

                    String age_category="";

                    if(c1.isChecked()){
                        age_category = "0-6 Month";
                    }else if(c2.isChecked()){
                        age_category = "6-12 Month";
                    }else if(c3.isChecked()){
                        age_category = "Adult";
                    }

                    boolean flag = false;
                    if((!ed1.getText().toString().isEmpty()) && (!ed2.getText().toString().isEmpty()) && (age_category != "")) {
                        flag = true;
                    }

                    if(flag) {
                        Toast.makeText(getApplicationContext(),
                                "Saving...", Toast.LENGTH_SHORT).show();

                        String message = ed1.getText().toString();
                        String message2 = ed2.getText().toString();
                        String message3 = age_category;
                        String message4 = ed4.getText().toString();
                        String message5 = ed5.getText().toString();
                        String message6 = ed6.getText().toString();
                        String message7 = ed7.getText().toString();
                        intent.putExtra(PET_NAME, message);
                        intent.putExtra(BREED_NAME, message2);
                        intent.putExtra(AGE_CATEGORY, message3);
                        intent.putExtra(HEIGHT, message4);
                        intent.putExtra(WEIGHT, message5);
                        intent.putExtra(VACCINE, message6);
                        intent.putExtra(LAST_DATE, message7);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "Please fill missing places",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Please choose only one Age Category",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
