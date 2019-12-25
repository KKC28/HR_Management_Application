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

public class NonHRHomePage extends AppCompatActivity {

    private EditText domain;
    private Button logout;
    private EditText gaps;
    private  EditText experience;
    private EditText numberofvacancy;
    private SQLiteDatabase sqLiteDatabase;
    private final String TAG="komal";

    private Button reportVacancy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_hrhome_page);

        domain=findViewById(R.id.domain);
        numberofvacancy=findViewById(R.id.numberOfVacancy);
        gaps=findViewById(R.id.gaps);
        experience=findViewById(R.id.experience);
        reportVacancy=findViewById(R.id.reportVacancy);
        logout=findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Employee.class));
            }
        });

        //createDatabase();

        reportVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportVacancy();
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

    private void createDatabase() {

        sqLiteDatabase=openOrCreateDatabase("vacancy",0,null);
        sqLiteDatabase
                .execSQL("create table if not exists "+
                        "vacancy(ID INTEGER PRIMARY KEY AUTOINCREMENT,domain varchar(50),numberofvacancy varchar(50),gaps varchar(50),experience varchar(50),bond varchar(50) default '0',salary varchar(50) default '0')");

        //sqLiteDatabase.execSQL("Delete from vacancy");

    }

    private void reportVacancy() {

        if(domain.length()==0 || numberofvacancy.length()==0 || gaps.length()==0 || experience.length()==0)
        {
            Toast.makeText(getApplicationContext(),"All fields are mandatory",Toast.LENGTH_SHORT).show();
            return;
        }


        sqLiteDatabase=openOrCreateDatabase("vacancy",0,null);
        sqLiteDatabase.execSQL("insert into vacancy(domain,numberofvacancy,gaps,experience) values('"+domain.getText().toString()+"','"+numberofvacancy.getText().toString()+"','"+gaps.getText().toString()+"','"+experience.getText().toString()+"')");
        Cursor cursor=sqLiteDatabase.rawQuery("select * from vacancy",null);
        while(cursor.moveToNext())
        {

            Log.d(TAG,cursor.getString(0).toString()+cursor.getString(1).toString()+cursor.getString(2).toString()+cursor.getString(3).toString()+cursor.getString(4).toString());
            //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
        }
        Toast.makeText(this,"Vacancy Recorded",Toast.LENGTH_SHORT).show();
        domain.getText().clear();
        numberofvacancy.getText().clear();
        gaps.getText().clear();
        experience.getText().clear();

    }
}
