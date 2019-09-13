package com.example.assignment_1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.assignment_1.R;

public class MainActivity extends AppCompatActivity {

    private Button gradesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUi();
        setupAction();
    }

    private void setupAction() {
        this.gradesButton.setOnClickListener((view)->navigateToGrades());
    }

    private void setupUi() {

        gradesButton = findViewById(R.id.button_grade);
    }

    private void navigateToGrades(){
        Intent myIntent = new Intent(getApplicationContext(), GradeActivity.class);
        startActivity(myIntent);
    }
}
