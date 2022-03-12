package com.firstapp.firebaseauthproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BloodGroup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_group);

        // Spinner element
        Spinner aSpinner = findViewById(R.id.aSpinner);

        // Spinner click listener
        aSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}