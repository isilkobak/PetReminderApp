package com.example.petrre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDB.db";
    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_NAME = "name";
    public static final String USERS_COLUMN_PASSWORD = "password";
    private HashMap hp;

    public static final String ANIMALS_TABLE_NAME = "animal";
    public static final String ANIMALS_COLUMN_ID = "id";
    public static final String ANIMALS_COLUMN_NAME = "name";
    public static final String ANIMALS_COLUMN_BREED = "breed_name";
    public static final String ANIMALS_COLUMN_AGE_CATEGORY = "age";
    public static final String ANIMALS_COLUMN_HEIGHT = "height";
    public static final String ANIMALS_COLUMN_WEIGHT = "weight";
    public static final String ANIMALS_COLUMN_VACCINE = "vaccine";
    public static final String ANIMALS_COLUMN_LAST_DATE = "last_date";


    public static final String VACCINE_TABLE_NAME = "vaccine";
    public static final String VACCINE_COLUMN_ID = "id";
    public static final String VACCINE_COLUMN_BREED = "breed";
    public static final String VACCINE_COLUMN_AGE_CATEGORY = "age_category";
    public static final String VACCINE_COLUMN_VACCINE = "vaccine";
    public static final String VACCINE_COLUMN_WEEK = "week";
    public static final String VACCINE_COLUMN_YEARLY = "yearly";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL(
                "create table users " +
                        "(id integer primary key, name text,password text)"
        );
        db.execSQL("DROP TABLE IF EXISTS animal");
        db.execSQL(
                "create table animal " +
                        "(id integer primary key, name text,breed_name text, age text, vaccine text, height real, weight real, last_date text)"
        );
        db.execSQL("DROP TABLE IF EXISTS vaccine");
        db.execSQL(
                "create table vaccine " +
                        "(id integer primary key, breed text,age_category text, vaccine text, week int, yearly int, date last_date)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS animal");
        db.execSQL("DROP TABLE IF EXISTS vaccine");
        onCreate(db);
    }


    public boolean insertUser (String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put(USERS_COLUMN_PASSWORD, password);
        db.insert(USERS_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertAnimal (String breed_name, String vaccine, String last_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(ANIMALS_COLUMN_NAME, name);
        contentValues.put(ANIMALS_COLUMN_BREED, breed_name);
        contentValues.put(ANIMALS_COLUMN_VACCINE, vaccine);
        //contentValues.put(ANIMALS_COLUMN_HEIGHT, height);
        //contentValues.put(ANIMALS_COLUMN_WEIGHT, weight);
        contentValues.put(ANIMALS_COLUMN_LAST_DATE, last_date);
        db.insert(ANIMALS_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertVaccine (String breed, String age_category, String vaccine, int week, int yearly){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VACCINE_COLUMN_BREED, breed);
        contentValues.put(VACCINE_COLUMN_AGE_CATEGORY, age_category);
        contentValues.put(VACCINE_COLUMN_VACCINE, vaccine);
        contentValues.put(VACCINE_COLUMN_WEEK, week);
        contentValues.put(VACCINE_COLUMN_YEARLY, yearly);
        db.insert(VACCINE_TABLE_NAME, null, contentValues);
        return true;
    }


    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public Cursor getAnimalData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from animal where id="+id+"", null );
        return res;
    }

    public Cursor getVaccineData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from vaccine where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
        return numRows;
    }

    public int numberOfAnimalRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ANIMALS_TABLE_NAME);
        return numRows;
    }

    public int numberOfVaccineRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, VACCINE_TABLE_NAME);
        return numRows;
    }

    public boolean updateUser (Integer id, String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put("password", password);
        db.update("users", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateAnimal (Integer id, String name, String breed_name, String age_category, float height, float weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ANIMALS_COLUMN_NAME, name);
        contentValues.put(ANIMALS_COLUMN_BREED, breed_name);
        contentValues.put(ANIMALS_COLUMN_AGE_CATEGORY, age_category);
        contentValues.put(ANIMALS_COLUMN_HEIGHT, height);
        contentValues.put(ANIMALS_COLUMN_WEIGHT, weight);
        db.update(ANIMALS_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateVaccine (String breed, String age_category, String vaccine, int week, int yearly) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VACCINE_COLUMN_BREED, breed);
        contentValues.put(VACCINE_COLUMN_AGE_CATEGORY, age_category);
        contentValues.put(VACCINE_COLUMN_VACCINE, vaccine);
        contentValues.put(VACCINE_COLUMN_WEEK, week);
        contentValues.put(VACCINE_COLUMN_YEARLY, yearly);
        db.update("vaccine", contentValues, "breed = ? ", new String[] { breed } );
        return true;
    }
   /* public boolean updateVaccineLastDate (String breed, String vaccine, String last_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VACCINE_COLUMN_LAST_DATE, String.valueOf(last_date));
        db.update("vaccine", contentValues, "breed = ? and vaccine = ?", new String[] { breed } );
        return true;
    }*/


    public Integer deleteUser (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteAnimal (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteVaccine (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("vaccine",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public void truncateUsers () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from users ");
    }

    public void truncateAnimals () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from animal ");
    }

    public void truncateVaccine () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from vaccine ");
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users", null );
        res.moveToFirst(); //çünkü res -1 den başlıyor .

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllPasswords() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_PASSWORD)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<VaccineTable> selectVaccines() {
        ArrayList<VaccineTable> vaccine_list = new ArrayList<VaccineTable>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from vaccine", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

           //Date last_date =new SimpleDateFormat("dd/MM/yyyy").parse(res.getString(res.getColumnIndex(VACCINE_COLUMN_LAST_DATE)));

            VaccineTable vaccineTable = new VaccineTable(res.getString(res.getColumnIndex(VACCINE_COLUMN_BREED)),
                    res.getString(res.getColumnIndex(VACCINE_COLUMN_AGE_CATEGORY)),
                                    res.getString(res.getColumnIndex(VACCINE_COLUMN_VACCINE)),
                                            res.getString(res.getColumnIndex(VACCINE_COLUMN_WEEK)));

            vaccine_list.add(vaccineTable);
            res.moveToNext();
        }

        return vaccine_list;

    }

    public ArrayList<AnimalTable> selectAnimal(){
        ArrayList<AnimalTable> animal_list = new ArrayList<AnimalTable>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from animal", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            //Date last_date =new SimpleDateFormat("dd/MM/yyyy").parse(res.getString(res.getColumnIndex(VACCINE_COLUMN_LAST_DATE)));

            AnimalTable animalTable = new AnimalTable(res.getString(res.getColumnIndex(ANIMALS_COLUMN_BREED)),
                    res.getString(res.getColumnIndex(ANIMALS_COLUMN_VACCINE)),
                    res.getString(res.getColumnIndex(ANIMALS_COLUMN_LAST_DATE)));

            animal_list.add(animalTable);
            res.moveToNext();
        }

        return animal_list;
    }



}
