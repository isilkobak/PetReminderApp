package com.example.petrre;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity  {
    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    DBHelper mydb;
    Button b1,b2;
    EditText ed1,ed2;
    TextView tx1;
    int counter = 3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        mydb.truncateUsers();
        mydb.insertUser("admin","pass");
        mydb.insertUser("admin2","pass2");



        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        b2 = (Button)findViewById(R.id.button2);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        ArrayList<String> user_list = new ArrayList<String>();
        user_list = mydb.getAllUsers();
        ArrayList<String> password_list = new ArrayList<String>();
        password_list = mydb.getAllPasswords();

        final ArrayList<UserTable> login_list = new ArrayList<UserTable>();
        for(int i=0; i<user_list.size(); i++){
            String user = user_list.get(i);
            String password = password_list.get(i);
            UserTable userTable = new UserTable(user, password);
            login_list.add(userTable);
        }

        final Intent intent = new Intent(this, DisplayActivity.class);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserTable userTable = new UserTable(ed1.getText().toString(),ed2.getText().toString());
                boolean flag = false;
                for(int i = 0; i<login_list.size(); i++){
                    if(login_list.get(i).getUser().matches(userTable.getUser()) && login_list.get(i).getPassword().matches(userTable.getPassword())){
                        flag = true;
                    }
                }
                if(flag) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    ArrayList<VaccineTable> vaccineTable_list = new ArrayList<VaccineTable>();
                    /*ArrayList<String> breed_list = new ArrayList<String>();
                    breed_list.set(0, "Golden Retriever");breed_list.set(1, "German Shepherd Dog");
                    breed_list.set(2, "Bulldog");breed_list.set(3, "Doberman Pinscher");
                    breed_list.set(4, "Rottweiler");breed_list.set(5, "Siberian Husky");
                    breed_list.set(6, "Chow Chow");breed_list.set(7, "Beagle");
                    breed_list.set(8, "Komondor");breed_list.set(9, "Pomeranian");
                    breed_list.set(10, "Pug");breed_list.set(11, "Papillon");
                    breed_list.set(12, "Jack Russell Terrier");breed_list.set(13, "Maltese");
                    breed_list.set(14, "Yorkshire Terrier");breed_list.set(15, "British Short Hair");
                    breed_list.set(16, "Bengal");breed_list.set(17, "Bombay");
                    breed_list.set(18, "Egyptian Mau");breed_list.set(19, "Van");
                    breed_list.set(20, "Oriental Short Hair");breed_list.set(21, "Tonkinese");
                    breed_list.set(22, "Ragamuffin");breed_list.set(23, "Siberian");
                    breed_list.set(24, "Persian");breed_list.set(25, "Sphynx");
                    breed_list.set(26, "Devon Rex");breed_list.set(27, "Scottish Fold ");breed_list.set(28, "Siamese");*/





                    String message = ed1.getText().toString();
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}