package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Offer_Discription extends AppCompatActivity {

    private String id;
    private TextView data;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteDatabase sqLiteDatabasetemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer__discription);
        id=getIntent().getStringExtra("id");
        data=findViewById(R.id.discription);

        sqLiteDatabase = openOrCreateDatabase("jobs", 0, null);
        Cursor cursortemp = sqLiteDatabase.rawQuery("select * from jobs", null);
        sqLiteDatabasetemp = openOrCreateDatabase("application", 0, null);
        Cursor cursor = sqLiteDatabasetemp.rawQuery("select * from application", null);

        while (cursor.moveToNext())
        {
            if(cursor.getString(0).equals(id))
            {
                while(cursortemp.moveToNext())
                {
                    if(cursor.getString(2).equals(cursortemp.getString(0)))
                    {
                        data.append("Domain: "+cursortemp.getString(1)+" Bond: "+cursortemp.getString(5)+" "+" Salary: "+cursortemp.getString(6));
                        break;
                    }
                }
                break;
            }
        }

    }
}
