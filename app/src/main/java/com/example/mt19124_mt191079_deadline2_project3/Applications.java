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

public class Applications extends AppCompatActivity {
    private ListView mlistView;
    private String TAG = "Komal";
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_list);
        mlistView = (ListView) findViewById(R.id.listview);
        populateListView();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HRHomePage.class));

    }

    private void populateListView() {

        ArrayList<String> listData = new ArrayList<>();
        sqLiteDatabase = openOrCreateDatabase("application", 0, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from application", null);
        while (cursor.moveToNext()) {
            if(cursor.getString(3).equals("APPLIED"))
            {
                listData.add(cursor.getString(0));
            }
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mlistView.setAdapter(adapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), mlistView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                sqLiteDatabase = openOrCreateDatabase("application", 0, null);
                Cursor cursor = sqLiteDatabase.rawQuery("select * from application", null);
                while (cursor.moveToNext()) {
                    if (cursor.getString(0).equals(mlistView.getItemAtPosition(i).toString())) {
                        Intent intent = new Intent(getApplicationContext(), Application_Recived.class);
                        intent.putExtra("id", cursor.getString(0).toString());
                        startActivity(intent);

                        Log.d(TAG, cursor.getString(1).toString() + cursor.getString(2).toString());
                        //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
                    }
                }

            }
        });

    }
}
