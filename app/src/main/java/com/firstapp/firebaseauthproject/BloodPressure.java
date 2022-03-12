package com.firstapp.firebaseauthproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BloodPressure extends AppCompatActivity {

    private TextView rstbtn;
    private TextView rstbtn1;
    private TextView rstbtn2;
    private int counter,counter1,counter2;
    @SuppressLint("NonConstantResourceId")
    private final View.OnClickListener clickListener =v -> {
        switch (v.getId()){
            case R.id.mnsbtn:
                minusCounter();
                break;
            case R.id.mnsbtn1:
                minusCounter1();
                break;
            case R.id.mnsbtn2:
                minusCounter2();
                break;
            case R.id.plsbtn:
                plusCounter();
                break;
            case R.id.plsbtn1:
                plusCounter1();
                break;
            case R.id.plsbtn2:
                plusCounter2();
                break;
            case R.id.rstbtn:
                initCounter();
                break;
            case R.id.rstbtn1:
                initCounter1();
                break;
            case R.id.rstbtn2:
                initCounter2();
                break;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);
        rstbtn =findViewById(R.id.rstbtn);
        Button mnsbtn=findViewById(R.id.mnsbtn);
        Button plsbtn=findViewById(R.id.plsbtn);
        mnsbtn.setOnClickListener(clickListener);
        plsbtn.setOnClickListener(clickListener);
        rstbtn.setOnClickListener(clickListener);
        rstbtn1 =findViewById(R.id.rstbtn1);
        Button mnsbtn1=findViewById(R.id.mnsbtn1);
        Button plsbtn1=findViewById(R.id.plsbtn1);
        mnsbtn1.setOnClickListener(clickListener);
        plsbtn1.setOnClickListener(clickListener);
        rstbtn1.setOnClickListener(clickListener);
        rstbtn2=findViewById(R.id.rstbtn2);
        Button mnsbtn2=findViewById(R.id.mnsbtn2);
        Button plsbtn2=findViewById(R.id.plsbtn2);
        mnsbtn2.setOnClickListener(clickListener);
        plsbtn2.setOnClickListener(clickListener);
        rstbtn2.setOnClickListener(clickListener);
        initCounter();
        initCounter1();
        initCounter2();
    }
    @SuppressLint("SetTextI18n")
    private void initCounter(){
        counter = 120;
        rstbtn.setText(counter + "");
    }
    @SuppressLint("SetTextI18n")
    private void plusCounter(){
        counter++;
        rstbtn.setText(counter + "");
    }
    @SuppressLint("SetTextI18n")
    private void minusCounter(){
        counter--;
        rstbtn.setText(counter + "");
    }
    @SuppressLint("SetTextI18n")
    private void initCounter1(){
        counter1 = 80;
        rstbtn1.setText(counter1 + "");
    }
    @SuppressLint("SetTextI18n")
    private void plusCounter1(){
        counter1++;
        rstbtn1.setText(counter1 + "");
    }
    @SuppressLint("SetTextI18n")
    private void minusCounter1(){
        counter1--;
        rstbtn1.setText(counter1 + "");
    }
    @SuppressLint("SetTextI18n")
    private void initCounter2(){
        counter2 = 75;
        rstbtn2.setText(counter2 + "");
    }
    @SuppressLint("SetTextI18n")
    private void plusCounter2(){
        counter2++;
        rstbtn2.setText(counter2 + "");
    }
    @SuppressLint("SetTextI18n")
    private void minusCounter2(){
        counter2--;
        rstbtn2.setText(counter2 + "");
    }
}