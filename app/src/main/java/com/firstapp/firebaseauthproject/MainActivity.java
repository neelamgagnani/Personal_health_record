package com.firstapp.firebaseauthproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public CardView card1, card2, card3, card4, card5, card6;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView nvDrawer=findViewById(R.id.nav_view);
        setupDrawerContent(nvDrawer);
        card1=findViewById(R.id.c1);
        card2=findViewById(R.id.c2);
        card3=findViewById(R.id.c3);
        card4=findViewById(R.id.c4);
        card5=findViewById(R.id.c5);
        card6=findViewById(R.id.c6);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    @SuppressLint("NonConstantResourceId")
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        int id=menuItem.getItemId();
        if (id == R.id.nav_profile) {
            Intent intent=new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_prescription) {
            Intent intent = new Intent(MainActivity.this, Prescriptions.class);
            startActivity(intent);
        } else if (id == R.id.nav_vitals) {
            Intent intent = new Intent(MainActivity.this, Vitals.class);
            startActivity(intent);
        } else if (id == R.id.nav_report) {
            Intent intent = new Intent(MainActivity.this, Report.class);
            startActivity(intent);
        } else if (id == R.id.nav_searchdoc) {
            Intent intent = new Intent(MainActivity.this, Searchable.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(MainActivity.this, History.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setMessage("Are You Sure You Want To Log Out?")
                    .setCancelable(true)//not compulsory to click yes or no
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseAuth.getInstance().signOut();//logout
                            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));//new activity login
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            android.app.AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else if (id == R.id.nav_share) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody="Share My App With You";
            String shareSub="your sub here";
            intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, "share using"));
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        } else if (id == R.id.nav_feedback) {
            Intent intent=new Intent(MainActivity.this, Feedback.class);
            startActivity(intent);
        }
        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.c1:
                i=new Intent(this, BMICal.class);
                startActivity(i);
                break;

            case R.id.c2:
                i=new Intent(this, BloodGroup.class);
                startActivity(i);
                break;

            case R.id.c3:
                i=new Intent(this, BloodPressure.class);
                startActivity(i);
                break;

            case R.id.c4:
                i=new Intent(this, HealthCal.class);
                startActivity(i);
                break;

            case R.id.c5:
                i=new Intent(this, PillRem.class);
                startActivity(i);
                break;

            case R.id.c6:
                i=new Intent(this, SugarLevel.class);
                startActivity(i);
                break;
        }
    }
}