package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Employee extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private Button back;
    private Button nonhrlogin;
    private EditText id;
    private EditText password;
    private Button hr;
    private Button non_hr;

    private final String TAG="Komal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        nonhrlogin=findViewById(R.id.nonhr_login);
        id=findViewById(R.id.nonhr_id);
        password=findViewById(R.id.nonhr_password);
        hr=findViewById(R.id.hr_login);
        non_hr=findViewById(R.id.nonhr_login);
        back=findViewById(R.id.homepage);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

        createDatabase();




        non_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nonhrHomePage();
            }
        });

        hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hrHomePage();
            }
        });


    }

    private void createDatabase() {

        sqLiteDatabase=openOrCreateDatabase("employee",0,null);
        sqLiteDatabase
                .execSQL("create table if not exists "+
                        "employee(id varchar(50) primary key,name varchar(50),password varchar(50),departmentid varchar(50))");

        sqLiteDatabase.execSQL("Delete from employee");

        sqLiteDatabase.execSQL("insert into employee values('"+"1"+"','"+"Komal"+"','"+"1"+"','"+"HR"+"')");
        sqLiteDatabase.execSQL("insert into employee values('"+"2"+"','"+"Prerna"+"','"+"2"+"','"+"NON-HR"+"')");
    }

    private void hrHomePage() {

        sqLiteDatabase=openOrCreateDatabase("employee",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from employee",null);

        boolean flag=true; // match not found

        while(cursor.moveToNext())
        {
            if(cursor.getString(0).toString().equals(id.getText().toString())
                    && cursor.getString(2).toString().equals(password.getText().toString() ))
            {
                //Log.d(TAG,"Login");
                if(cursor.getString(3).toString().equals(hr.getText().toString()))
                {
                    //Log.d(TAG,"kk");
                    startActivity(new Intent(this, HRHomePage.class));
                    flag = false;
                }
                else
                {
                    //Log.d(TAG,"Dept");
                    Toast.makeText(this,"You don't belong to the choosen department",Toast.LENGTH_SHORT).show();
                }
            }
            //Log.d(TAG,cursor.getString(0).toString()+id.getText().toString()+cursor.getString(2).toString()+password.getText().toString()+cursor.getString(3).toString());
            //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
        }
        if(flag)
        {
            Toast.makeText(this,"Invalid username or password or Department",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {



    }


    private void nonhrHomePage() {

        sqLiteDatabase=openOrCreateDatabase("employee",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from employee",null);

        boolean flag=true; // match not found

        while(cursor.moveToNext())
        {
            if(cursor.getString(0).toString().equals(id.getText().toString())
                    && cursor.getString(2).toString().equals(password.getText().toString() ))
            {
                //Log.d(TAG,"Login");
                if(cursor.getString(3).toString().equals(non_hr.getText().toString()))
                {
                        //Log.d(TAG,"kk");
                        startActivity(new Intent(this, NonHRHomePage.class));
                        flag = false;
                }
                else
                {
                    Toast.makeText(this,"You don't belong to the choosen department",Toast.LENGTH_SHORT).show();
                }
            }


            //Log.d(TAG,cursor.getString(0).toString()+id.getText().toString()+cursor.getString(2).toString()+password.getText().toString()+cursor.getString(3).toString());
            //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
        }
        if(flag)
        {
            Toast.makeText(this,"Invalid username or password or departmennt",Toast.LENGTH_SHORT).show();
        }


    }


}
