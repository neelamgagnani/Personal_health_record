package com.firstapp.firebaseauthproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private FirebaseAuth auth;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        inputEmail = findViewById(R.id.EditTextSurname);
        Button btnReset = findViewById(R.id.textView3);
        auth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(v -> {
            String email = inputEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplication(), "Enter your mail address", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPasswordActivity.this, "We send you an e-mail", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    public void NavigateSignUp(View v) {
        Intent inent = new Intent(this, SignUpActivity.class);
        startActivity(inent);
    }
}