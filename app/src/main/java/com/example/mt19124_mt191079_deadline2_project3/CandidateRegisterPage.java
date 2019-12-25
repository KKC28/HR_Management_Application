package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CandidateRegisterPage extends AppCompatActivity {

    private Button candidateRegister;
    private Button candidateLogin;
    public SQLiteDatabase sqLiteDatabase;
    private EditText name;
    private EditText email;
    private  EditText password;
    private EditText dob;
    private Button homepage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_register);

        candidateLogin=findViewById(R.id.Login);
        candidateRegister=findViewById(R.id.Register);
        homepage=findViewById(R.id.homepage);

        //createDatabase();

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        candidateRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeCandiatePage();
            }
        });

        candidateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginCandiatePage();
            }
        });




    }

    @Override
    public void onBackPressed() {


    }

    public void createDatabase()
    {
        sqLiteDatabase=openOrCreateDatabase("candidate",0,null);

        sqLiteDatabase
                .execSQL("create table if not exists "+
                        "candidate(name varchar(50),email varchar(50) primary key,password varchar(50),dob varchar(50))");
        //sqLiteDatabase.execSQL("Delete from candidate");


    }

    public void homeCandiatePage()
    {
        name=(EditText)findViewById(R.id.candidate_name);
        password=(EditText)findViewById(R.id.candidate_password);
        email=(EditText)findViewById(R.id.candidate_email);
        dob=(EditText)findViewById(R.id.candidate_dob);

        sqLiteDatabase=openOrCreateDatabase("candidate",0,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from candidate",null);
        while(cursor.moveToNext())
        {
            if(cursor.getString(1).toString().equals(email.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"Already Registered!! please Login",Toast.LENGTH_SHORT).show();
            }
        }

        if(email.getText().toString().matches(""))
        {
            Toast.makeText(getApplicationContext(),"Email is mandatory",Toast.LENGTH_SHORT).show();

        }
        else
        {
            sqLiteDatabase.execSQL("insert into candidate(name,email,password,dob) values('"+name.getText().toString()+"','"+email.getText().toString()+"','"+password.getText().toString()+"','"+dob.getText().toString()+"')");
            startActivity(new Intent(this,CandidateLoginPage.class));
        }



    }

    public void loginCandiatePage()
    {

        startActivity(new Intent(this,CandidateLoginPage.class));

    }
}
