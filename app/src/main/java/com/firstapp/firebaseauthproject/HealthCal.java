package com.firstapp.firebaseauthproject;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class HealthCal extends AppCompatActivity {

    EditText etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_cal);
        etDate = findViewById(R.id.et_date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    HealthCal.this, (view, year12, month12, day1) -> {
                        month12 = month12 +1;
                        String date = day1 +"/"+ month12 +"/"+ year12;
                        etDate.setText(date);
                    },year,month,day);
            datePickerDialog.show();
        });
    }
}