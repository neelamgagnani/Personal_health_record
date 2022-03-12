package com.firstapp.firebaseauthproject;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import java.util.Calendar;

public class History extends AppCompatActivity {
    EditText etDate,etDate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        etDate = findViewById(R.id.et_date);
        etDate1 = findViewById(R.id.et_date1);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    History.this, (view, year12, month12, day1) -> {
                month12 = month12 +1;
                String date = day1 +"/"+ month12 +"/"+ year12;
                etDate.setText(date);
            },year,month,day);
            datePickerDialog.show();
        });
        etDate1.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    History.this, (view, year12, month12, day1) -> {
                month12 = month12 +1;
                String date = day1 +"/"+ month12 +"/"+ year12;
                etDate1.setText(date);
            },year,month,day);
            datePickerDialog.show();
        });
    }
}
