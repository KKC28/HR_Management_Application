package com.example.mt19124_mt191079_deadline2_project3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class List_Job extends AppCompatActivity {


    private ListView mlistView;
    private String TAG="Komal";
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_list);
        mlistView=(ListView)findViewById(R.id.listview);
        populateListView();

    }

    private void populateListView() {

        ArrayList<String> listData=new ArrayList<>();
        sqLiteDatabase=openOrCreateDatabase("vacancy",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from vacancy",null);
        while(cursor.moveToNext())
        {
            listData.add(cursor.getString(0));
        }

            ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mlistView.setAdapter(adapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),mlistView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                sqLiteDatabase=openOrCreateDatabase("vacancy",0,null);
                Cursor cursor=sqLiteDatabase.rawQuery("select * from vacancy",null);
                while(cursor.moveToNext())
                {
                    if(cursor.getString(0).equals(mlistView.getItemAtPosition(i).toString())) {
                        Intent intent = new Intent(getApplicationContext(), JobProfile.class);
                        intent.putExtra("id", cursor.getString(0).toString());
                        intent.putExtra("domain", cursor.getString(1).toString());
                        intent.putExtra("numberOfvacncy", cursor.getString(2).toString());
                        intent.putExtra("gaps", cursor.getString(3).toString());
                        intent.putExtra("experince", cursor.getString(4).toString());
                        startActivity(intent);

                        Log.d(TAG, cursor.getString(0).toString() + cursor.getString(1).toString()+cursor.getString(2).toString()+cursor.getString(3).toString());
                        //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
                    }
                }

            }
        });

    }

}