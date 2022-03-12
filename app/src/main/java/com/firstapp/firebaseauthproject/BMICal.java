package com.firstapp.firebaseauthproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMICal extends AppCompatActivity {
    EditText weight, height;
    TextView resulttext;
    String calculation, BMIresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_cal);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resulttext = findViewById(R.id.result);
    }

    public void calculateBMI(View view) {
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();

        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2) / 100;

        float bmi = weightValue / (heightValue * heightValue);
        if (bmi < 16) {
            BMIresult = "Severely Under Weight";
        } else if (bmi < 18.5) {
            BMIresult = "Under Weight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = "Normal Weight";
        } else if (bmi >= 25 && bmi <= 29.9) {
            BMIresult = "OverWeight";
        } else {
            BMIresult = "Obese";
        }
        calculation = "Result:\n\n" + bmi +"\n" +BMIresult;
        resulttext.setText(calculation);
    }
}