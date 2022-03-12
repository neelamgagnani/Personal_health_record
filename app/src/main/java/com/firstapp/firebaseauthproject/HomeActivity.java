package com.firstapp.firebaseauthproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//    import android.support.annotation.NonNull;
//  import android.support.v7.app.AppCompatActivity;
//    import android.support.design.widget.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_favorites:
                            Toast.makeText(getApplicationContext(), "Favorites", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.action_home:
                            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.action_profile:
                            Intent inentProfile = new Intent(HomeActivity.this, ProfileActivity.class);
                            startActivity(inentProfile);
                            break;
                    }
                    return true;
                });
    }
}