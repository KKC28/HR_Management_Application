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

public class JobProfile extends AppCompatActivity {

    private EditText domain;
    private EditText number;
    private EditText gaps;
    private EditText experince;
    private EditText bond;
    private EditText salary;
    private EditText id;

    private final String TAG="Komal";


    private Button publish;

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_profile);

        domain=findViewById(R.id.domain);
        number=findViewById(R.id.numberOfVacancy);
        gaps=findViewById(R.id.gaps);
        experince=findViewById(R.id.experience);
        bond=findViewById(R.id.bond);
        salary=findViewById(R.id.salary);
        publish=findViewById(R.id.publish);
        id=findViewById(R.id.id);
        domain.setEnabled(false);
        id.setEnabled(false);
        experince.setEnabled(false);
        gaps.setEnabled(false);
        number.setEnabled(false);


        domain.setText(getIntent().getStringExtra("domain"));
        number.setText(getIntent().getStringExtra("numberOfvacncy"));
        gaps.setText(getIntent().getStringExtra("gaps"));
        experince.setText(getIntent().getStringExtra("experince"));
        id.setText(getIntent().getStringExtra("id"));








        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishVacancy();
            }
        });


        //Toast.makeText(this,getIntent().getStringExtra("domain"),Toast.LENGTH_SHORT).show();


    }

    private void publishVacancy() {


        sqLiteDatabase=openOrCreateDatabase("jobs",0,null);

        /*sqLiteDatabase
                .execSQL("create table if not exists "+
                        "jobs(ID varchar(50) primary key,domain varchar(50),numberofvacancy varchar(50),gaps varchar(50),experience varchar(50),bond varchar(50),salary varchar(50))");*/
        Cursor cursor=sqLiteDatabase.rawQuery("select * from jobs",null);
        boolean flag =false;

        while(cursor.moveToNext())
        {
            if(cursor.getString(0).equals(id.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"Already published",Toast.LENGTH_SHORT).show();
                flag=true;
                break;
            }

        }
        if(flag==false) {

            sqLiteDatabase.execSQL("insert into jobs values('" + id.getText().toString() + "','" + domain.getText().toString() + "','" + number.getText().toString() + "','" + gaps.getText().toString() + "','" + experince.getText().toString() + "','" + bond.getText().toString() + "','" + salary.getText().toString() + "')");
            Toast.makeText(getApplicationContext(),"Published",Toast.LENGTH_SHORT).show();
        }

        /*sqLiteDatabase.execSQL("update vacancy set bond='"+bond.getText().toString()+"',salary='"+salary.getText().toString()+"'where ID="+id.getText().toString()+";");
        Toast.makeText(getApplicationContext(),"Profile Created",Toast.LENGTH_SHORT).show();*/

        cursor=sqLiteDatabase.rawQuery("select * from jobs",null);
        while(cursor.moveToNext())
        {
            Log.d(TAG,cursor.getString(0)+cursor.getString(5)+cursor.getString(6));
            //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
        }



    }
}
