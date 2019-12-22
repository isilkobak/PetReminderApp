package com.example.petrre;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;


import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.opencsv.CSVReader;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.example.petrre.ReminderActivity.*;

public class NotificationView extends Activity{
    DBHelper mydb;
    String TAG ="main";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        mydb = new DBHelper(this);
        ArrayList<AnimalTable> animalTables = mydb.selectAnimal();
        ArrayList<VaccineTable> vaccineTables = mydb.selectVaccines();

        Intent intent = getIntent();
        String pet = intent.getStringExtra(DisplayActivity.PET_NAME);
        String breed = intent.getStringExtra(DisplayActivity.BREED_NAME);
        String age_category = intent.getStringExtra(DisplayActivity.AGE_CATEGORY);
        Boolean flag1 = intent.getBooleanExtra(FLAG1, false);
        Boolean flag2 = intent.getBooleanExtra(FLAG2, false);
        Boolean flag3 = intent.getBooleanExtra(FLAG3, false);
        Boolean flag4 = intent.getBooleanExtra(FLAG4, false);


        TextView t = (TextView) findViewById(R.id.textView);
        String sourceString4 = "<b> HERE ARE FOR SOME TIPS FOR YOU : </b> ";
        t.append(Html.fromHtml(sourceString4));
        t.append("\n");
        ArrayList<String> Breed = new ArrayList<String>();
        ArrayList<String> Age_Category = new ArrayList<String>();
        ArrayList<String> Meal = new ArrayList<String>();
        ArrayList<String> Exercise = new ArrayList<String>();
        try {
            InputStream myInput;
            // initialize asset manager
            AssetManager assetManager = getAssets();
            //  open excel sheet

            myInput = assetManager.open("excel.xls");
            // Create a POI File System object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // We now need something to iterate through the cells.
            Iterator<Row> rowIter = mySheet.rowIterator();
            int rowno = 0;
            // textView2.append("\n");

            while (rowIter.hasNext()) {
                Log.e(TAG, " row no " + rowno);
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if (rowno != 0) {
                    int i = 0;
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno = 0;

                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno == 0) {
                            Breed.add(myCell.toString());
                        } else if (colno == 1) {
                            Age_Category.add(myCell.toString());
                        } else if (colno == 2) {
                            Meal.add(myCell.toString());
                        } else {
                            Exercise.add(myCell.toString());
                        }
                        i++;
                        colno++;
                        Log.e(TAG, " Index :" + myCell.getColumnIndex() + " -- " + myCell.toString());
                    }

                }

                rowno++;
            }
        } catch (Exception e) {
            Log.e(TAG, "error " + e.toString());
        }

        for (int i = 0; i < Breed.size(); i++){
            if((Breed.get(i).equals(breed) && (Age_Category.get(i).equals(age_category)))){
                if(flag1) {
                    t.append("Feed with "+ Meal.get(i) + " please :)"+"\n");
                    t.append("\n");
                }else if(flag4) {
                    t.append(Exercise.get(i) + "\n");
                    t.append("\n");
                }
            }
        }
        if(flag2){
            t.append("Please clean its toilet if it is fill up :) \n");
            t.append("\n");
        }

        //Vaccine Reminder
        if(flag3) {
            String sourceString5 = "<b> Next vaccines: </b> ";
            t.append(Html.fromHtml(sourceString5));
            ArrayList<VaccineTable> resultVaccines = new ArrayList<VaccineTable>();
            int index = 0;
            for (int i = 0; i < vaccineTables.size(); i++) {
                if (vaccineTables.get(i).breed.contains(breed)) {
                    VaccineTable vaccine = new VaccineTable(null, null, vaccineTables.get(i).vaccine, vaccineTables.get(i).week);
                    resultVaccines.add(vaccine);

                }
            }


            HashMap<String, String> animalMap = new HashMap<String, String>();
            int id = 2;
            for (int i = 0; i < animalTables.size(); i++) {
                if (i == 0) {
                    animalMap.put(animalTables.get(0).vaccine + "/" + 1, animalTables.get(0).last_date);
                } else {
                    if (animalTables.get(i).vaccine.contains(animalTables.get(i - 1).vaccine)) {
                        animalMap.put(animalTables.get(i).vaccine + "/" + id, animalTables.get(i).last_date);
                        id++;
                    } else {
                        id = 1;
                        animalMap.put(animalTables.get(i).vaccine + "/" + id, animalTables.get(i).last_date);
                        id++;
                    }
                }

            }


            HashMap<String, String> vaccineMap = new HashMap<String, String>();
            int id2 = 2;
            for (int i = 0; i < resultVaccines.size(); i++) {
                if (i == 0) {
                    vaccineMap.put(resultVaccines.get(0).vaccine + "/" + 1, resultVaccines.get(0).week);
                } else {
                    if (resultVaccines.get(i).vaccine.contains(resultVaccines.get(i - 1).vaccine)) {
                        vaccineMap.put(resultVaccines.get(i).vaccine + "/" + id2, resultVaccines.get(i).week);
                        id2++;
                    } else {
                        id2 = 1;
                        vaccineMap.put(resultVaccines.get(i).vaccine + "/" + id2, resultVaccines.get(i).week);
                        id2++;
                    }
                }

            }


            for (String keyAnimal : animalMap.keySet()) {
                for (String keyVaccine : vaccineMap.keySet()) {
                    if (keyAnimal.matches(keyVaccine)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            vaccineMap.replace(keyVaccine, animalMap.get(keyAnimal));
                        }
                    }
                }
            }

            HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
            for (String r : vaccineMap.keySet()) {
                //String result = vaccineMap.get(r).substring(0, vaccineMap.get(r).length() - 1);
                int we = Integer.parseInt(vaccineMap.get(r));
                resultMap.put(r, we);

            }
            t.append("\n");
            String sourceString = "<b> In 4 Months: </b> ";
            t.append(Html.fromHtml(sourceString));
            t.append("\n");
            for(String i : resultMap.keySet()) {
                if (resultMap.get(i) < 17) {
                    //
                    t.append("Next: " + i + " " +
                            "vaccine is : " + resultMap.get(i) + " weeks after\n");


                }
            }
            t.append("\n");
            String sourceString2 = "<b> In 4 and 8 Months: </b> ";
            t.append(Html.fromHtml(sourceString2));
            t.append("\n");
            for(String i : resultMap.keySet()) {
                if ((resultMap.get(i) > 16) && (resultMap.get(i) < 33)) {
                    //
                    t.append("Next: " + i + " " +
                            "vaccine is : " + resultMap.get(i) + " weeks after\n");


                }
            }

            t.append("\n");
            String sourceString3 = "<b> After 8 Months: </b> ";
            t.append(Html.fromHtml(sourceString3));
            t.append("\n");
            for(String i : resultMap.keySet()) {
                if (resultMap.get(i) > 32){
                    t.append("Next: " + i + " " +
                            "vaccine is : " + resultMap.get(i) + " weeks after\n");

                }

            }

        }
    }
}