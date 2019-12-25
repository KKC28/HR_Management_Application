package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button candidate;
    private Button employee;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        candidate=findViewById(R.id.candidate);
        employee=findViewById(R.id.employee);
        createDatabase();

        candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                candidatePage();
            }
        });
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeePage();
            }
        });

    }

    private void createDatabase() {

        sqLiteDatabase=openOrCreateDatabase("jobs",0,null);
        sqLiteDatabase
                .execSQL("create table if not exists "+
                        "jobs(ID varchar(50) primary key,domain varchar(50),numberofvacancy varchar(50),gaps varchar(50),experience varchar(50),bond varchar(50),salary varchar(50))");
        sqLiteDatabase=openOrCreateDatabase("vacancy",0,null);
        sqLiteDatabase
                .execSQL("create table if not exists "+
                        "vacancy(ID INTEGER PRIMARY KEY AUTOINCREMENT,domain varchar(50),numberofvacancy varchar(50),gaps varchar(50),experience varchar(50),bond varchar(50) default '0',salary varchar(50) default '0')");
        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        sqLiteDatabase
                .execSQL("create table if not exists "+
                        "application(ID INTEGER PRIMARY KEY AUTOINCREMENT,candidateid varchar(50),jobid varchar(50),status varchar(50) default 'NOT APPLIED',writtenmarks varchar(50) default 0,interviewmarks varchar(50) default 0,url default 'NOT Sepcified')");
        sqLiteDatabase=openOrCreateDatabase("candidate",0,null);
        sqLiteDatabase
                .execSQL("create table if not exists "+
                        "candidate(name varchar(50),email varchar(50) primary key,password varchar(50),dob varchar(50))");


        //sqLiteDatabase.execSQL("Delete from vacancy");

    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }



    public void candidatePage()
    {
        startActivity(new Intent(this, CandidateRegisterPage.class));
    }

    public void employeePage()
    {

        startActivity(new Intent(this, Employee.class));

    }
}
