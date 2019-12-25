package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateInterview extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private String id;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_interview);

        textView=findViewById(R.id.Enter_interview_marks);
        button=findViewById(R.id.interview);

        //marks1=Integer.valueOf(textView.getText().toString());


        id=getIntent().getStringExtra("id");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });


    }

    private void update() {

        sqLiteDatabase = openOrCreateDatabase("application", 0, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from application", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(id)) {
                sqLiteDatabase.execSQL("update application set interviewmarks='"+textView.getText().toString()+"' where id='"+id+"';");
                Toast.makeText(getApplicationContext(),Integer.valueOf(textView.getText().toString())+"Komal",Toast.LENGTH_SHORT).show();
                if(Integer.valueOf(textView.getText().toString())>80)
                {
                    sqLiteDatabase.execSQL("update application set status='Hired' where id='"+id+"';");
                    Toast.makeText(getApplicationContext(),"recorded",Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                {
                    sqLiteDatabase.execSQL("update application set status='Not Hired' where id='"+id+"';");
                    Toast.makeText(getApplicationContext(),"recorded",Toast.LENGTH_SHORT).show();
                    break;
                }

            }
        }

    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(),InterviewExam.class));

    }
}
