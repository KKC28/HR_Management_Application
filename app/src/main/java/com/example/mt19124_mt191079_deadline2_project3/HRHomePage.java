package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class HRHomePage extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private Button jobProfile;
    private Button application;
    private Button interview;
    private Button finalist;
    private final String TAG="komal";
    private Button wriiten;
    private Button export;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrhome_page);

        jobProfile=findViewById(R.id.jobProfile);
        application=findViewById(R.id.applications);
        wriiten=findViewById(R.id.write);
        interview=findViewById(R.id.interview);
        finalist=findViewById(R.id.finallist);
        export=findViewById(R.id.export);
        logout=findViewById(R.id.logout);

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent (getApplicationContext(),Export.class));

            }
        });

        interview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),InterviewExam.class));
            }
        });

        finalist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FinalHiring.class));
            }
        });

        wriiten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Wrriten_Marks.class));
            }
        });

        application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Applications.class));



            }
        });

        jobProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //createJobProfile();
                startActivity(new Intent(HRHomePage.this,List_Job.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Employee.class));
            }
        });


    }

    @Override
    public void onBackPressed() {

    }





    private void wriitenexam() {
        startActivity(new Intent(getApplicationContext(),Wrriten_Marks.class));
    }

    private void createJobProfile() {

        sqLiteDatabase=openOrCreateDatabase("vacancy",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from vacancy",null);
        while(cursor.moveToNext())
        {
            Intent intent=new Intent(this,JobProfile.class);
            intent.putExtra("domain",cursor.getString(0).toString());
            intent.putExtra("numberOfvacncy",cursor.getString(1).toString());
            intent.putExtra("gaps",cursor.getString(2).toString());
            intent.putExtra("experince",cursor.getString(3).toString());
            startActivity(intent);

            Log.d(TAG,cursor.getString(1).toString()+cursor.getString(2).toString());
            //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
        }

    }
}
