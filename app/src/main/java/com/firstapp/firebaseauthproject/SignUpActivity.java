package com.firstapp.firebaseauthproject;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.net.Uri;
        import android.nfc.Tag;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.common.SignInButton;
        import com.google.android.gms.common.api.ApiException;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.util.HashMap;
        import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText Name,PhoneNum,EmailAddress,Password;
    Button RegisterBtn;
    SignInButton sign_in_button;
    TextView AlreadyLogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    private static int RC_SIGN_IN = 100;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name         = findViewById(R.id.editText2);
        PhoneNum      = findViewById(R.id.editText3);
        EmailAddress    = findViewById(R.id.editText);
        Password        = findViewById(R.id.editText4);
        RegisterBtn     = findViewById(R.id.button);
        AlreadyLogin    = findViewById(R.id.textView2);
        //sign_in_button = findViewById(R.id.sign_in_button);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        fStore = FirebaseFirestore.getInstance();


        //all (do not touch)
        if (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= EmailAddress.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String name = Name.getText().toString();
                String phonenum = PhoneNum.getText().toString();


                if (TextUtils.isEmpty(name))
                {
                    Name.setError("Name required");
                    return;
                }

                if (TextUtils.isEmpty(phonenum))
                {
                    PhoneNum.setError("Phone Number required");
                    return;
                }

                if (TextUtils.isEmpty(email)) //email empty
                {
                    EmailAddress.setError("Email required");
                    return;
                }


                //password required
                if (TextUtils.isEmpty(password))
                {
                    Password.setError("Password required");
                    return;
                }


                //check password length
                if (Password.length() < 6)
                {
                    Password.setError("Password must be 6 character long");
                    return;
                }
                //progress bar visible
                progressBar.setVisibility(View.VISIBLE);

                //register in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            userID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("phone number",phonenum);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: user Profile is created for " + userID);
                                }
                            });

                            //start activity
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        //Already Login.
        AlreadyLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
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