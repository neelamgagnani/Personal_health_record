package com.firstapp.firebaseauthproject;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText mEmailAddress2,mPassword2;
    Button mloginBtn;
    TextView alreadySignUp,mForgotPassword;
    FirebaseAuth fAuth;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mEmailAddress2    = findViewById(R.id.editText);
        mPassword2        = findViewById(R.id.editText2);
        mloginBtn     = findViewById(R.id.button);
        alreadySignUp    = findViewById(R.id.textView2);
        fAuth = FirebaseAuth.getInstance();
        progressBar2 = findViewById(R.id.progressBar2);
        mForgotPassword = findViewById(R.id.textView3);

        mloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= mEmailAddress2.getText().toString().trim();
                String password = mPassword2.getText().toString().trim();

                if (TextUtils.isEmpty(email)) //email empty
                {
                    mEmailAddress2.setError("Email required");
                    return;
                }

                if (TextUtils.isEmpty(password))
                {
                    mPassword2.setError("Password required");
                    return;
                }

                if (password.length() < 6)
                {
                    mPassword2.setError("Password must be 6 character long");
                    return;
                }

                progressBar2.setVisibility(View.INVISIBLE);

                //authenticate user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });

        //Already have account
        alreadySignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                finish();
            }
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter registered email");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract email and send reset link

                        String mail= resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Reset link sent to registered email", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error! Reset link not sent. " + e.getMessage() , Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Close dialogue
                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(true)//not compulsory to click yes or no
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        finishAffinity();
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
        //super.onBackPressed();
    }
}