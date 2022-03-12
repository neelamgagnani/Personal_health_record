package com.firstapp.firebaseauthproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.Objects;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        showDialog();
    }

    private void showDialog(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Feedback Form");
        dialog.setMessage("Provide Us Your Valuable Feedback");

        LayoutInflater inflater = LayoutInflater.from(this);
        View reg_layout = inflater.inflate(R.layout.activity_feedback, null);

        final MaterialEditText edtEmail =reg_layout.findViewById(R.id.edtEmail);
        final MaterialEditText edtName =reg_layout.findViewById(R.id.edtName);
        final MaterialEditText edtFeedback =reg_layout.findViewById(R.id.edtFeedback);
        dialog.setView(reg_layout);

        dialog.setPositiveButton( "SEND", (dialog1, which) -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(edtEmail.getText()).toString())){
                Toast.makeText(Feedback.this,"Please Type Your E-mail..", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Objects.requireNonNull(edtName.getText()).toString())){
                Toast.makeText(Feedback.this,"Name Field Can Not Be Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Objects.requireNonNull(edtFeedback.getText()).toString())){
                Toast.makeText(Feedback.this,"Feedback Field Can Not Be Empty..", Toast.LENGTH_SHORT).show();
            }
            });
        dialog.setNegativeButton("CANCEL", (dialog12, which) -> dialog12.dismiss());
        dialog.show();
        }
}