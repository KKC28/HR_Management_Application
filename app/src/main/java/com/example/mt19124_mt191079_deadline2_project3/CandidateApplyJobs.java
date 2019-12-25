package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CandidateApplyJobs extends AppCompatActivity {

    private EditText domain;
    private EditText id;
    private EditText salary;
    private EditText number;
    private EditText gaps;
    private EditText experince;
    private EditText bond;

    private  String name;

    private final String TAG="Komal";


    private Button apply;

    private SQLiteDatabase sqLiteDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_apply_jobs);

        domain=findViewById(R.id.domain);
        id=findViewById(R.id.id);
        number=findViewById(R.id.numberOfVacancy);
        gaps=findViewById(R.id.gaps);
        experince=findViewById(R.id.experience);
        bond=findViewById(R.id.bond);
        salary=findViewById(R.id.salary);
        id.setEnabled(false);
        domain.setEnabled(false);
        number.setEnabled(false);
        gaps.setEnabled(false);
        bond.setEnabled(false);
        salary.setEnabled(false);
        experince.setEnabled(false);

        apply=findViewById(R.id.apply);
        Toast.makeText(getApplicationContext(),getIntent().getStringExtra("candidate"),Toast.LENGTH_SHORT).show();

        name=getIntent().getStringExtra("candidate");


        domain.setText(getIntent().getStringExtra("domain"));
        number.setText(getIntent().getStringExtra("numberOfvacncy"));
        gaps.setText(getIntent().getStringExtra("gaps"));
        experince.setText(getIntent().getStringExtra("experince"));
        id.setText(getIntent().getStringExtra("id"));
        bond.setText(getIntent().getStringExtra("bond"));
        salary.setText(getIntent().getStringExtra("salary"));


        //createDatabase();



        //readJobsDatabase();


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addApplications();


            }
        });
    }

    private void createDatabase() {


        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        sqLiteDatabase
                .execSQL("create table if not exists "+
                        "application(ID INTEGER PRIMARY KEY AUTOINCREMENT,candidateid varchar(50),jobid varchar(50),status varchar(50) default 'NOT APPLIED',writtenmarks varchar(50) default 0,interviewmarks varchar(50) default 0)");

    }


    private void addApplications() {

        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from application",null);
        boolean flag=false;
        while(cursor.moveToNext())
        {
            if(cursor.getString(1).toString().equals(name) && cursor.getString(2).toString().equals(id.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"You have already applied for the job",Toast.LENGTH_SHORT).show();
                flag=true;
                break;
            }
        }
        if(flag==false)
        {
            sqLiteDatabase.execSQL("insert into application(candidateid,jobid,status) values('"+name+"','"+id.getText().toString()+"','APPLIED');");
            Toast.makeText(getApplicationContext(),"Applied",Toast.LENGTH_SHORT).show();

        }

        cursor=sqLiteDatabase.rawQuery("select * from application",null);
        while(cursor.moveToNext())
        {
            Log.d(TAG,cursor.getString(1).toString()+cursor.getString(2).toString());
        }


    }

    private void readJobsDatabase() {

        sqLiteDatabase=openOrCreateDatabase("jobs",0,null);

        Cursor cursor=sqLiteDatabase.rawQuery("select * from jobs",null);

        while(cursor.moveToNext())
        {
            id.setText((cursor.getString(0)).toString());
            domain.setText(cursor.getString(1).toString());
            number.setText(cursor.getString(2).toString());
            gaps.setText(cursor.getString(3).toString());
            experince.setText(cursor.getString(4).toString());
            bond.setText(cursor.getString(5).toString());
            salary.setText(cursor.getString(6).toString());

            Log.d(TAG,cursor.getString(4)+cursor.getString(5));
            //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
        }

    }
}
