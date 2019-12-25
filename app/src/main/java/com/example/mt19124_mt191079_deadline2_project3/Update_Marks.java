package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Update_Marks extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private String id;
    private SQLiteDatabase sqLiteDatabase;
    private int marks1=0;
    private String s;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__marks);

        textView=findViewById(R.id.Enter_wriiten_marks);
        button=findViewById(R.id.marks);

        //marks1=Integer.valueOf(textView.getText().toString());


        id=getIntent().getStringExtra("id");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });


    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(),Wrriten_Marks.class));

    }

    private void update() {

        sqLiteDatabase = openOrCreateDatabase("application", 0, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from application", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(id)) {
                //s=textView.getText().toString();
                //n=Integer.parseInt(textView.getText().toString());
                sqLiteDatabase.execSQL("update application set writtenmarks='"+textView.getText().toString()+"' where id='"+id+"';");
                Toast.makeText(getApplicationContext(),Integer.valueOf(textView.getText().toString())+"Komal",Toast.LENGTH_SHORT).show();
                if(Integer.valueOf(textView.getText().toString())>60)
                {
                    sqLiteDatabase.execSQL("update application set status='Interview Shortlist' where id='"+id+"';");
                    Toast.makeText(getApplicationContext(),"recorded",Toast.LENGTH_SHORT).show();
                    button.setEnabled(false);
                    break;
                }
                else
                {
                    sqLiteDatabase.execSQL("update application set status='Disqualified_In_Interview' where id='"+id+"';");
                    Toast.makeText(getApplicationContext(),"recorded",Toast.LENGTH_SHORT).show();
                    button.setEnabled(false);
                    break;
                }

            }
        }
    }
}
