package com.example.androidapp;

import static Service.EntropyCalculator.calculateAndPrintEntropy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Controller.DataBaseHandler;
import Model.MachineLearning;
import Service.DecisionTree;

public class HomeActivity extends AppCompatActivity {
    TextView welc,parag;
    private DataBaseHandler dataBaseHandler;
    private static int splash_timeout = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataBaseHandler = new DataBaseHandler(this, "machine_learning", null, 1);
        List<MachineLearning> machineLearningList = dataBaseHandler.getAllData();
        DecisionTree decisionTree = calculateAndPrintEntropy(machineLearningList);


        welc = findViewById(R.id.welcomeText);
        parag = findViewById(R.id.welcomeparagraph);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent splashIntent = new Intent(HomeActivity.this,MainActivity.class);
                splashIntent.putExtra("decisionTree",decisionTree);
                startActivity(splashIntent);
                finish();
            }
        },splash_timeout);
        Animation myAnimation1 = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.animation2);
        welc.startAnimation(myAnimation1);
        Animation myAnimation2 = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.animation1);
        parag.startAnimation(myAnimation2);
    }
}