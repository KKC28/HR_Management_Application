package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;

public class Job_Applied_Status extends AppCompatActivity {
    private String name;
    private String status;
    private String id;
    private TextView textView;
    private Button button;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button ButtonInterview;
    private TextView link;
    private SQLiteDatabase sqLiteDatabase;
    private TextView label;
    private String TAG="KO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__applied__status);

        textView=findViewById(R.id.jobStatus);
        button=findViewById(R.id.wriitten);
        radioGroup=findViewById(R.id.gen_radio_group);
        label=findViewById(R.id.status_radio);
        ButtonInterview=findViewById(R.id.interview);
        link=findViewById(R.id.link);

        radioGroup.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        label.setVisibility(View.INVISIBLE);
        ButtonInterview.setVisibility(View.INVISIBLE);
        link.setVisibility(View.INVISIBLE);


        name=getIntent().getStringExtra("candidate");
        status=getIntent().getStringExtra("status");
        id=getIntent().getStringExtra("id");

        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from application",null);
        while(cursor.moveToNext()) {
            if (cursor.getString(0).toString().equals(id)) {
                if (cursor.getString(3).equals("Interview Shortlist")) {

                    label.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    ButtonInterview.setVisibility(View.VISIBLE);


                }
                else if(cursor.getString(3).equals("Written Shortlisted"))
                {
                    radioGroup.setVisibility(View.VISIBLE);
                    label.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    link.setVisibility(View.VISIBLE);
                    link.setText("Test URL:" +cursor.getString(6).toString());

                }

            }
        }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    Log.d(TAG,"updaterad");
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    Log.d(TAG,radioButton.getText().toString());
                }
            });


        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG,"update");
                    if (radioGroup.getCheckedRadioButtonId() == -1)
                    {
                        Toast.makeText(getApplicationContext(),"Please select an option",Toast.LENGTH_SHORT).show();
                        // no radio buttons are checked
                    }
                    else
                    {
                        update();
                        // one of the radio buttons is checked
                    }
                    //update();
                }
            });

            ButtonInterview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //updateinterview();
                    if (radioGroup.getCheckedRadioButtonId() == -1)
                    {
                        Toast.makeText(getApplicationContext(),"Please select an option",Toast.LENGTH_SHORT).show();
                        // no radio buttons are checked
                    }
                    else
                    {
                        updateinterview();
                        // one of the radio buttons is checked
                    }
                }
            });



        textView.setText("Application Status : "+status);





        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
            }
        });*/


        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });*/








    }

    private void update() {

        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from application",null);
        boolean flag=false;
        while(cursor.moveToNext())
        {
            Log.d(TAG,cursor.getString(0)+id+cursor.getString(1)+name);
            if(cursor.getString(0).toString().equals(id) && cursor.getString(1).toString().equals(name))
            {
                if(radioButton.getText().toString().equals("Accept")) {
                    sqLiteDatabase.execSQL("update application set status='Wriiten Accepted' where id='" + id + "' and candidateid='"+name+"';");
                    Toast.makeText(getApplicationContext(), "recorded", Toast.LENGTH_SHORT).show();
                    button.setEnabled(false);
                    break;
                }
                else if(radioButton.getText().toString().equals("Reject"))
                {
                    //Log.d(TAG,"INDIA");
                    sqLiteDatabase.execSQL("update application set status='Wriiten Rejected' where id='" + id + "'and candidateid='"+name+"';");
                    Toast.makeText(getApplicationContext(), "recorded", Toast.LENGTH_SHORT).show();
                    button.setEnabled(false);
                    break;
                }
            }
        }
    }

    private void updateinterview() {

        sqLiteDatabase=openOrCreateDatabase("application",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from application",null);
        boolean flag=false;
        while(cursor.moveToNext())
        {
            Log.d(TAG,cursor.getString(0)+id+cursor.getString(1)+name);
            if(cursor.getString(0).toString().equals(id) && cursor.getString(1).toString().equals(name))
            {
                if(radioButton.getText().toString().equals("Accept")) {
                    sqLiteDatabase.execSQL("update application set status='Intreview Accepted' where id='" + id + "' and candidateid='"+name+"';");
                    Toast.makeText(getApplicationContext(), "recorded", Toast.LENGTH_SHORT).show();
                    ButtonInterview.setEnabled(false);
                    break;
                }
                else if(radioButton.getText().toString().equals("Reject"))
                {
                    //Log.d(TAG,"INDIA");
                    sqLiteDatabase.execSQL("update application set status='Intreview Rejected' where id='" + id + "'and candidateid='"+name+"';");
                    Toast.makeText(getApplicationContext(), "recorded", Toast.LENGTH_SHORT).show();
                    ButtonInterview.setEnabled(false);
                    break;
                }
            }
        }
    }


}
