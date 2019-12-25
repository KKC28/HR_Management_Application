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

public class HiredCandiadtes extends AppCompatActivity {

    private TextView textView;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteDatabase sqLiteDatabasetemp;
    private Button offer;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hired_candiadtes);

        id=getIntent().getStringExtra("id");
        offer=findViewById(R.id.gnerate);


        textView=findViewById(R.id.finalhire);
        sqLiteDatabase = openOrCreateDatabase("candidate", 0, null);
        Cursor cursortemp = sqLiteDatabase.rawQuery("select * from candidate", null);
        sqLiteDatabasetemp = openOrCreateDatabase("application", 0, null);
        Cursor cursor = sqLiteDatabasetemp.rawQuery("select * from application", null);

        while (cursor.moveToNext())
        {
            if(cursor.getString(0).equals(id))
            {
                while(cursortemp.moveToNext())
                {
                    if(cursor.getString(1).equals(cursortemp.getString(1)))
                    {
                        textView.append("Name : "+cursortemp.getString(0)+" Email id: "+cursortemp.getString(1)+" Date of birth "+cursortemp.getString(3));
                        break;
                    }
                }
                break;
            }
        }

        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabasetemp = openOrCreateDatabase("application", 0, null);
                Cursor cursor = sqLiteDatabasetemp.rawQuery("select * from application", null);

                while (cursor.moveToNext())
                {
                    if(cursor.getString(0).equals(id))
                    {
                        sqLiteDatabasetemp.execSQL("update application set status='Offer Letter Sent' where id='" + id +"';");
                        Toast.makeText(getApplicationContext(), "recorded", Toast.LENGTH_SHORT).show();
                        offer.setEnabled(false);
                        break;
                    }
                }

            }
        });


    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(),FinalHiring.class));



    }

}
