package com.example.mt19124_mt191079_deadline2_project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CandidateHomePage extends AppCompatActivity {
    private Button status;
    private  Button apply;
    private Button logout;
    private Button letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_home_page);

        status=findViewById(R.id.status);
        apply=findViewById(R.id.candidatechcekprofile);
        logout=findViewById(R.id.logout);
        letter=findViewById(R.id.letter);

        letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OfferLetter.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CandidateLoginPage.class));
            }
        });





        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStatus();
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkJobProfile();
            }
        });


    }
    @Override
    public void onBackPressed() {


    }



    private void checkJobProfile() {

        Intent intent=new Intent(getApplicationContext(),Jobs.class);
        intent.putExtra("candidate",getIntent().getStringExtra("candidate"));
        Toast.makeText(getApplicationContext(),getIntent().getStringExtra("candidate"), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }

    private void checkStatus() {
        Intent intent=new Intent(getApplicationContext(),CandidateCheckStatus.class);
        intent.putExtra("candidate",getIntent().getStringExtra("candidate"));
        Toast.makeText(getApplicationContext(),getIntent().getStringExtra("candidate"), Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}
