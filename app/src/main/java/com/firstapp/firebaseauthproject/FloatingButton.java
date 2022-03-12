package com.firstapp.firebaseauthproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatingButton extends AppCompatActivity {

FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_button);
        fab = findViewById(R.id.f_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatingButton.this,"you clicked fab",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FloatingButton.this, BloodPressure.class);
                    startActivity(intent);
                intent=new Intent(FloatingButton.this, History.class);
                startActivity(intent);
                intent=new Intent(FloatingButton.this, PillRem.class);
                startActivity(intent);
                intent=new Intent(FloatingButton.this, SugarLevel.class);
                startActivity(intent);
                intent=new Intent(FloatingButton.this, History.class);
                startActivity(intent);
            }
        });
    }
}