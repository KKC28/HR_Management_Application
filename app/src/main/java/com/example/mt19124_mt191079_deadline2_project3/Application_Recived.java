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
import android.widget.TextView;
import android.widget.Toast;

public class Application_Recived extends AppCompatActivity {

    private TextView textView;
    private String id;
    private SQLiteDatabase sqLiteDatabase;
    private Button accept;
    private Button reject;
    private String TAG="Komal";

    private EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application__recived);

        textView=findViewById(R.id.text);
        id=getIntent().getStringExtra("id");

        accept=findViewById(R.id.accept);
        reject=findViewById(R.id.reject);

        url=findViewById(R.id.link);


        sqLiteDatabase = openOrCreateDatabase("application", 0, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from application", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(id)) {

                textView.append("Application ID: "+cursor.getString(0)+" Candidate Email: "+cursor.getString(1)+" Job Id: "+cursor.getString(2)+" Status :"+cursor.getString(3));
                Log.d(TAG, cursor.getString(1).toString() + cursor.getString(2).toString());

                //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
            }
        }

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sqLiteDatabase = openOrCreateDatabase("application", 0, null);
                Cursor cursor = sqLiteDatabase.rawQuery("select * from application", null);

                while (cursor.moveToNext())
                {
                    if (cursor.getString(0).toString().equals(id))
                    {
                        //Log.d(TAG,id+"reject");
                        sqLiteDatabase.execSQL("update application set status='Disqualified' where id='"+id+"';");
                        Toast.makeText(getApplicationContext(),"recorded",Toast.LENGTH_SHORT).show();
                        accept.setEnabled(false);
                        reject.setEnabled(false);
                        break;


                    }

                }



            }
        });


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sqLiteDatabase = openOrCreateDatabase("application", 0, null);
                Cursor cursor = sqLiteDatabase.rawQuery("select * from application", null);

                while (cursor.moveToNext())
                {
                    if (cursor.getString(0).toString().equals(id))
                    {
                        //Log.d(TAG,id+"accep");
                        sqLiteDatabase.execSQL("update application set status='Written Shortlisted' where id='"+id+"';");
                        sqLiteDatabase.execSQL("update application set url='"+url.getText().toString()+"' where id='"+id+"';");
                        Log.d(TAG,cursor.getString(3));
                        Toast.makeText(getApplicationContext(),"recorded",Toast.LENGTH_SHORT).show();
                        accept.setEnabled(false);
                        reject.setEnabled(false);
                        break;


                    }

                }



            }
        });





    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Applications.class));

    }
}
