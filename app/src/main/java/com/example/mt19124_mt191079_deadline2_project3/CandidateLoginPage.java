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

public class CandidateLoginPage extends AppCompatActivity {

    private Button candidateLogin;
    private SQLiteDatabase sqLiteDatabase;
    private final String TAG="Komal";
    private Button homepage;


    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_login_page);

        email=findViewById(R.id.candidate_emaill);
        password=findViewById(R.id.candidate_passwordd);

        homepage=findViewById(R.id.homepage);
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });



        candidateLogin=findViewById(R.id.candidate_login);
        candidateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                candidateHomePage();
            }
        });
    }

    @Override
    public void onBackPressed() {


    }



    private void candidateHomePage() {
        sqLiteDatabase=openOrCreateDatabase("candidate",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from candidate",null);
        boolean flag=true;
        Log.d(TAG,email.getText().toString());

        while(cursor.moveToNext())
        {
            if(cursor.getString(1).toString().equals(email.getText().toString()) && cursor.getString(2).toString().equals(password.getText().toString() ))
            {
                Intent intent= new Intent(getApplicationContext(),CandidateHomePage.class);
                intent.putExtra("candidate",email.getText().toString());
                startActivity(intent);
                flag=false;
                break;
            }

            //Log.d(TAG,cursor.getString(1).toString()+cursor.getString(2).toString()+email.getText().toString()+password.getText().toString());
            //textView.append("\n"+"X: "+cursor.getString(0).toString()+" y: "+cursor.getString(1).toString()+" z: "+cursor.getString(2).toString() +" timestamp: "+cursor.getString(3).toString());
        }
        if(flag)
        {
            Toast.makeText(this,"Invalid username or password",Toast.LENGTH_SHORT).show();
        }
    }
}
