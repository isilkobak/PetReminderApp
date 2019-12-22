package com.example.petrre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.os.Bundle;
import android.widget.Button;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.petrre.DisplayActivity.AGE_CATEGORY;
import static com.example.petrre.DisplayActivity.BREED_NAME;
import static com.example.petrre.DisplayActivity.PET_NAME;

public class ReminderActivity extends AppCompatActivity {
    public static final String FLAG1 = "Checkbox1 Flag";
    public static final String FLAG2 = "Checkbox2 Flag";
    public static final String FLAG3 = "Checkbox3 Flag";
    public static final String FLAG4 = "Checkbox4 Flag";
    public static final String PET = "Pet";
    Button b1;
    TimePicker timePicker1;
    CheckBox c1,c2,c3,c4;
    DBHelper mydb;
    int year,month,week,day;
    int l_year,l_month,l_week,l_day;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        c1 = (CheckBox) findViewById(R.id.ch1);
        c2 = (CheckBox) findViewById(R.id.ch2);
        c3 = (CheckBox) findViewById(R.id.ch3);
        c4 = (CheckBox) findViewById(R.id.ch4);


        Intent intent = getIntent();
        String pet = intent.getStringExtra(DisplayActivity.PET_NAME);

        if(pet.equals("Cat")){
            c4.setEnabled(false);
        }else if(pet.equals("Fish")){
            c3.setEnabled(false);
            c4.setEnabled(false);
        }





        b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addNotification();
            }
        });
    }
    private void addNotification()  {

        Intent intent = getIntent();
        String pet = intent.getStringExtra(DisplayActivity.PET_NAME);
        String breed = intent.getStringExtra(BREED_NAME);
        String age_category = intent.getStringExtra(DisplayActivity.AGE_CATEGORY);


        mydb = new DBHelper(this);

        mydb.truncateVaccine();
        mydb.insertVaccine("Golden Retriever", "All", "Canine Distemper", 11, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Canine Distemper", 13, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Canine Distemper", 60, 1);
        mydb.insertVaccine("Golden Retriever", "All", "DHLPP", 11, 1);
        mydb.insertVaccine("Golden Retriever", "All", "DHLPP", 13, 1);
        mydb.insertVaccine("Golden Retriever", "All", "DHLPP", 60, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Hepatitis", 11, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Hepatitis", 13, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Hepatitis", 60, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Canine Parvovirus", 15, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Canine Parvovirus", 17, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Canine Parvovirus", 60, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Lestospirosis", 15, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Lestospirosis", 17, 1);
        mydb.insertVaccine("Golden Retriever", "All", "Lestospirosis", 60, 1);
        mydb.insertVaccine("Bulldog", "All", "Canine Distemper", 6, 0);
        mydb.insertVaccine("Bulldog", "All", "Canine Distemper", 8, 0);
        mydb.insertVaccine("Bulldog", "All", "Canine Distemper", 60, 0);
        mydb.insertVaccine("Bulldog", "All", "DHPP", 10, 1);
        mydb.insertVaccine("Bulldog", "All", "DHPP", 14, 1);
        mydb.insertVaccine("Bulldog", "All", "DHPP", 48, 1);
        mydb.insertVaccine("Bulldog", "All", "Lestospirosis", 12, 1);
        mydb.insertVaccine("Bulldog", "All", "Lestospirosis", 48, 1);
        mydb.insertVaccine("Bulldog", "All", "Rabies", 10,1);
        mydb.insertVaccine("Bulldog", "All", "Rabies", 48,1);
        mydb.insertVaccine("Bulldog", "All", "Parainfluenza Bordetella", 10,1);
        mydb.insertVaccine("Bulldog", "All", "Parainfluenza Bordetella", 14,1);
        mydb.insertVaccine("Bulldog", "All", "Parainfluenza Bordetella", 48,1);
        mydb.insertVaccine("Bulldog", "All", "Lyme Disease", 10,1);
        mydb.insertVaccine("Bulldog", "All", "Lyme Disease", 14,1);
        mydb.insertVaccine("Bulldog", "All", "Lyme Disease", 48,1);
        mydb.insertVaccine("Doberman Pinscher", "All", "Canine Distemper", 10,1 );
        mydb.insertVaccine("Doberman Pinscher", "All", "Hepatitis", 7, 1);
        mydb.insertVaccine("Doberman Pinscher", "All", "Hepatitis", 13,1);
        mydb.insertVaccine("Doberman Pinscher", "All", "DHPP", 16,1);
        mydb.insertVaccine("Rottweiler", "All", "DHPP", 6,1);
        mydb.insertVaccine("Rottweiler", "All", "DHLPP", 9,1);
        mydb.insertVaccine("Rottweiler", "All", "DHLPP", 10,1);
        mydb.insertVaccine("Rottweiler", "All", "DHLPP", 12,1);
        mydb.insertVaccine("Rottweiler", "All", "DHLPP", 13,1);
        mydb.insertVaccine("Rottweiler", "All", "Rabies", 14,1);
        mydb.insertVaccine("Rottweiler", "All", "Rabies", 16,1);
        mydb.insertVaccine("Rottweiler", "All", "Parainfluenza Bordetella", 9,1);
        mydb.insertVaccine("Rottweiler", "All", "Parainfluenza Bordetella", 10,1);
        mydb.insertVaccine("Rottweiler", "All", "Lyme Disease", 12,1);
        mydb.insertVaccine("Rottweiler", "All", "Lyme Disease", 16,1);
        mydb.insertVaccine("Siberian Husky", "All", "DHPP", 6,1);
        mydb.insertVaccine("Siberian Husky", "All", "DHPP", 8,1);
        mydb.insertVaccine("Siberian Husky", "All", "DHPP", 11,1);
        mydb.insertVaccine("Siberian Husky", "All", "DHPP", 13,1);
        mydb.insertVaccine("Siberian Husky", "All", "DHPP", 15,1);
        mydb.insertVaccine("Siberian Husky", "All", "DHPP", 17,1);
        mydb.insertVaccine("Siberian Husky", "All", "DHPP", 12,1);
        mydb.insertVaccine("Siberian Husky", "All", "DHPP", 24,1);
        mydb.insertVaccine("Chow Chow", "All", "DHLPP", 6,1);
        mydb.insertVaccine("Chow Chow", "All", "DHLPP", 8,1);
        mydb.insertVaccine("Chow Chow", "All", "DHLPP", 11,1);
        mydb.insertVaccine("Chow Chow", "All", "DHLPP", 13,1);
        mydb.insertVaccine("Chow Chow", "All", "DHLPP", 15,1);
        mydb.insertVaccine("Chow Chow", "All", "DHLPP", 17,1);
        mydb.insertVaccine("Chow Chow", "All", "Rabies", 12,1);
        mydb.insertVaccine("Chow Chow", "All", "Rabies", 24,1);
        mydb.insertVaccine("Beagle", "All", "DHPP", 6,1);
        mydb.insertVaccine("Beagle", "All", "DHPP", 8,1);
        mydb.insertVaccine("Beagle", "All", "Canine Parvovirus", 5,1);
        mydb.insertVaccine("Beagle", "All", "Lestospirosis", 16,1);
        mydb.insertVaccine("Beagle", "All", "Rabies", 12,1);

        ArrayList<VaccineTable> vaccineTables = mydb.selectVaccines();


        ArrayList<AnimalTable> animalTables = mydb.selectAnimal();


        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        int hour = timePicker1.getCurrentHour();
        int minute = timePicker1.getCurrentMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 15);


        Calendar rightNow = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            // year = rightNow.getWeekYear();
            date = rightNow.getTime();
            year = rightNow.get(Calendar.YEAR);
            month = rightNow.get(Calendar.MONTH) + 1;
            week = rightNow.get(Calendar.WEEK_OF_MONTH);
            day = rightNow.get(Calendar.DAY_OF_MONTH);
        }
        month = month * 4;
        month = month + week - 1;

        mydb.truncateAnimals();
        int subweek;
        int vaccine_week;
        String w;

        for (int i = 0; i < vaccineTables.size(); i++) {
            for (int j = 0; j < animalTables.size(); j++) {
                if((!animalTables.get(j).vaccine.isEmpty()) && (!animalTables.get(j).vaccine.isEmpty())) {
                    l_day = 0;
                    l_year = 0;
                    l_month = 0;
                    w = null;
                    if ((animalTables.get(j).breed_name.equals(vaccineTables.get(i).breed)) && (animalTables.get(j).vaccine.equals(vaccineTables.get(i).vaccine))) {
                        String last = animalTables.get(j).last_date;
                        //Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(last);
                        //cal.setTime(date);

                        String[] result = last.split("/");
                        l_day = Integer.parseInt(result[0]);
                        l_month = Integer.parseInt(result[1]);
                        l_year = Integer.parseInt(result[2]);

                        cal.set(l_year, l_month, l_day);
                        l_week = cal.get(Calendar.WEEK_OF_MONTH);

                        if (l_year == year) {
                            l_month = l_month * 4;
                            l_month = l_month + l_week - 1;
                            if (l_month < month) {
                                subweek = month - l_month;
                                vaccine_week = Integer.parseInt(vaccineTables.get(i).week);
                                if (vaccine_week > subweek) {
                                    int r_week = vaccine_week - subweek;
                                    w = String.valueOf(r_week);
                                    mydb.insertAnimal(vaccineTables.get(i).breed, animalTables.get(j).vaccine, w);
                                }
                            }
                        }

                    }
                }
            }
        }



        final Intent resultIntent = new Intent(this, NotificationReciever.class);
        resultIntent.putExtra(BREED_NAME, breed);
        resultIntent.putExtra(PET_NAME, pet);
        resultIntent.putExtra(AGE_CATEGORY,age_category);

        if(pet.equals("Cat")){
            c4.setEnabled(false);

                    if(c1.isChecked()){
                        boolean fc1 = true;
                        resultIntent.putExtra(FLAG1, fc1);
                    }else{
                        boolean fc1 = false;
                        resultIntent.putExtra(FLAG1, fc1);
                    }

                    if(c2.isChecked()){
                        boolean fc2 = true;
                        resultIntent.putExtra(FLAG2, fc2);
                    }else{
                        boolean fc2 = false;
                        resultIntent.putExtra(FLAG2, fc2);
                    }


                    if(c3.isChecked()){
                        boolean fc3 = true;
                        resultIntent.putExtra(FLAG3, fc3);
                    }else{
                        boolean fc3 = false;
                        resultIntent.putExtra(FLAG3, fc3);
                    }

        }else if(pet.equals("Fish")){

            c3.setEnabled(false);
            c4.setEnabled(false);

                    if(c1.isChecked()){
                        boolean fc1 = true;
                        resultIntent.putExtra(FLAG1, fc1);
                    }else{
                        boolean fc1 = false;
                        resultIntent.putExtra(FLAG1, fc1);
                    }

                    if(c2.isChecked()){
                        boolean fc2 = true;
                        resultIntent.putExtra(FLAG2, fc2);
                    }else{
                        boolean fc2 = false;
                        resultIntent.putExtra(FLAG2, fc2);
                    }


        }else {

                    if(c1.isChecked()){
                        boolean fc1 = true;
                        resultIntent.putExtra(FLAG1, fc1);
                    }else{
                        boolean fc1 = false;
                        resultIntent.putExtra(FLAG1, fc1);
                    }

                    if(c2.isChecked()){
                        boolean fc2 = true;
                        resultIntent.putExtra(FLAG2, fc2);
                    }else{
                        boolean fc2 = false;
                        resultIntent.putExtra(FLAG2, fc2);
                    }


                    if(c3.isChecked()){
                        boolean fc3 = true;
                        resultIntent.putExtra(FLAG3, fc3);
                    }else{
                        boolean fc3 = false;
                        resultIntent.putExtra(FLAG3, fc3);
                    }


                    if(c4.isChecked()){
                        boolean fc4 = true;
                        resultIntent.putExtra(FLAG4, fc4);
                    }else{
                        boolean fc4 = false;
                        resultIntent.putExtra(FLAG4, fc4);
                    }
                }


        PendingIntent resultPendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, resultPendingIntent);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), resultPendingIntent);

    }




}
