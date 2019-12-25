package com.example.mt19124_mt191079_deadline2_project3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Export extends AppCompatActivity {
    private Button interview;
    private Button write;
    private Button finallist;
    private Button report;
    private SQLiteDatabase sqLiteDatabase;
    private TextView output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        finallist=findViewById(R.id.Hired_List);
        report=findViewById(R.id.Full_report);
        interview=findViewById(R.id.interviewres);
        output=findViewById(R.id.output);
        write=findViewById(R.id.wriitenres);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED  && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        }


        finallist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportfinal();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportreport();

            }
        });
        interview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportinterview();

            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportwrite();

            }
        });

    }

    @Override
    public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults){
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this,"granted",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(this,"not granted",Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private void exportreport() {

        try {
            sqLiteDatabase=openOrCreateDatabase("application",0,null);
            Cursor c = sqLiteDatabase.rawQuery("select * from application", null);
            int rowcount = 0;
            int colcount = 0;

            File folder = new File(Environment.getExternalStorageDirectory() + "/Reports");

            boolean var = false;
            if (!folder.exists())
                var = folder.mkdir();

            final String file_name = folder.toString() + "/" + "TotalReport.csv";


            //Toast.makeText(this,Environment.getExternalStorageState()+"KO",Toast.LENGTH_SHORT).show();
            // the name of the file to export with
            FileWriter fw = new FileWriter(file_name);

            BufferedWriter bw = new BufferedWriter(fw);
            rowcount = c.getCount();
            colcount = c.getColumnCount();
            if (rowcount > 0) {
                c.moveToFirst();

                for (int i = 0; i < colcount; i++) {
                    if (i != colcount - 1) {

                        bw.write(c.getColumnName(i) + ",");

                    } else {

                        bw.write(c.getColumnName(i));

                    }
                }
                bw.newLine();

                for (int i = 0; i < rowcount; i++) {
                    c.moveToPosition(i);

                    for (int j = 0; j < colcount; j++) {
                        if (j != colcount - 1)
                            bw.write(c.getString(j) + ",");
                        else
                            bw.write(c.getString(j));
                    }
                    bw.newLine();
                }
                bw.flush();
            }
        } catch (Exception ex) {


        } finally {

        }





    }

    private void exportinterview() {
        try {
            sqLiteDatabase=openOrCreateDatabase("application",0,null);
            Cursor c = sqLiteDatabase.rawQuery("select ID,candidateid,jobid,interviewmarks from application", null);
            int rowcount = 0;
            int colcount = 0;

            File folder = new File(Environment.getExternalStorageDirectory() + "/Reports");

            boolean var = false;
            if (!folder.exists())
                var = folder.mkdir();

            final String file_name = folder.toString() + "/" + "InterviewResults.csv";


            //Toast.makeText(this,Environment.getExternalStorageState()+"KO",Toast.LENGTH_SHORT).show();
            // the name of the file to export with
            FileWriter fw = new FileWriter(file_name);

            BufferedWriter bw = new BufferedWriter(fw);
            rowcount = c.getCount();
            colcount = c.getColumnCount();
            if (rowcount > 0) {
                c.moveToFirst();

                for (int i = 0; i < colcount; i++) {
                    if (i != colcount - 1) {

                        bw.write(c.getColumnName(i) + ",");

                    } else {

                        bw.write(c.getColumnName(i));

                    }
                }
                bw.newLine();

                for (int i = 0; i < rowcount; i++) {
                    c.moveToPosition(i);

                    for (int j = 0; j < colcount; j++) {
                        if (j != colcount - 1)
                            bw.write(c.getString(j) + ",");
                        else
                            bw.write(c.getString(j));
                    }
                    bw.newLine();
                }
                bw.flush();
            }
        } catch (Exception ex) {


        } finally {

        }

        Toast.makeText(getApplicationContext(),"Exported",Toast.LENGTH_SHORT).show();
        output.setText("");
        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        Cursor c = sqLiteDatabase.rawQuery("select ID,candidateid,jobid,interviewmarks from application", null);
        while (c.moveToNext())
        {
            output.append("Application ID : "+c.getString(0)+" Candidate Email: "+c.getString(1)+" Job ID : "+c.getString(2)+" Interview Marks: "+c.getString(3));
        }
    }

    private void exportwrite() {
        try {
            sqLiteDatabase=openOrCreateDatabase("application",0,null);
            Cursor c = sqLiteDatabase.rawQuery("select ID,candidateid,jobid,writtenmarks from application", null);
            int rowcount = 0;
            int colcount = 0;

            File folder = new File(Environment.getExternalStorageDirectory() + "/Reports");

            boolean var = false;
            if (!folder.exists())
                var = folder.mkdir();

            final String file_name = folder.toString() + "/" + "Written results.csv";


            //Toast.makeText(this,Environment.getExternalStorageState()+"KO",Toast.LENGTH_SHORT).show();
            // the name of the file to export with
            FileWriter fw = new FileWriter(file_name);

            BufferedWriter bw = new BufferedWriter(fw);
            rowcount = c.getCount();
            colcount = c.getColumnCount();
            if (rowcount > 0) {
                c.moveToFirst();

                for (int i = 0; i < colcount; i++) {
                    if (i != colcount - 1) {

                        bw.write(c.getColumnName(i) + ",");

                    } else {

                        bw.write(c.getColumnName(i));

                    }
                }
                bw.newLine();

                for (int i = 0; i < rowcount; i++) {
                    c.moveToPosition(i);

                    for (int j = 0; j < colcount; j++) {
                        if (j != colcount - 1)
                            bw.write(c.getString(j) + ",");
                        else
                            bw.write(c.getString(j));
                    }
                    bw.newLine();
                }
                bw.flush();
            }
        } catch (Exception ex) {


        } finally {

        }
        Toast.makeText(getApplicationContext(),"Exported",Toast.LENGTH_SHORT).show();
        output.setText("");
        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        Cursor c = sqLiteDatabase.rawQuery("select ID,candidateid,jobid,writtenmarks from application", null);
        while (c.moveToNext())
        {
            output.append("Application ID : "+c.getString(0)+" Candidate Email: "+c.getString(1)+" Job ID : "+c.getString(2)+" Written Marks: "+c.getString(3));
        }
    }

    private void exportfinal() {

        try {
            sqLiteDatabase=openOrCreateDatabase("application",0,null);
            Cursor c = sqLiteDatabase.rawQuery("select * from application where status='Hired'", null);
            int rowcount = 0;
            int colcount = 0;

            File folder = new File(Environment.getExternalStorageDirectory() + "/Reports");

            boolean var = false;
            if (!folder.exists())
                var = folder.mkdir();

            final String file_name = folder.toString() + "/" + "FinalHiring.csv";


            //Toast.makeText(this,Environment.getExternalStorageState()+"KO",Toast.LENGTH_SHORT).show();
            // the name of the file to export with
            FileWriter fw = new FileWriter(file_name);

            BufferedWriter bw = new BufferedWriter(fw);
            rowcount = c.getCount();
            colcount = c.getColumnCount();
            if (rowcount > 0) {
                c.moveToFirst();

                for (int i = 0; i < colcount; i++) {
                    if (i != colcount - 1) {

                        bw.write(c.getColumnName(i) + ",");

                    } else {

                        bw.write(c.getColumnName(i));

                    }
                }
                bw.newLine();

                for (int i = 0; i < rowcount; i++) {
                    c.moveToPosition(i);

                    for (int j = 0; j < colcount; j++) {
                        if (j != colcount - 1)
                            bw.write(c.getString(j) + ",");
                        else
                            bw.write(c.getString(j));
                    }
                    bw.newLine();
                }
                bw.flush();
            }
        } catch (Exception ex) {


        } finally {

        }
        Toast.makeText(getApplicationContext(),"Exported",Toast.LENGTH_SHORT).show();
        output.setText("");
        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        Cursor c = sqLiteDatabase.rawQuery("select * from application", null);
        while (c.moveToNext())
        {
            output.append("Candidate : "+c.getString(1)+" hired for job: "+c.getString(2));
        }


    }
}
