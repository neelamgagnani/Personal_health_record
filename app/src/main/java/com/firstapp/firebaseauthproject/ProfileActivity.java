package com.firstapp.firebaseauthproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    TextView name,phonenum,email;
    Button donebtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.profile_name_textView);
        phonenum = findViewById(R.id.profile_phoneno_textView);
        email = findViewById(R.id.textViewEmailAdress);
        donebtn = findViewById(R.id.donebtn);
        profilePic = findViewById(R.id.profile_pic_imageView);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        //get profile data from firestore
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){
                            String emailResult = task.getResult().getString("email");
                            String nameResult = task.getResult().getString("name");
                            String phonenumberResult = task.getResult().getString("phone number");

                            email.setText(emailResult);
                            name.setText(nameResult);
                            phonenum.setText(phonenumberResult);
                        }
                        else {
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                    }
                });

        //done button onclick
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey =UUID.randomUUID().toString();
        StorageReference mountainsRef = storageReference.child("images/" + randomKey);

        mountainsRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    pd.dismiss();
                    Snackbar.make(findViewById(android.R.id.content),"Image Uploaded.", Snackbar.LENGTH_LONG).show();
                })
                .addOnFailureListener(exception -> {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(),"Failed To Upload",Toast.LENGTH_LONG).show();
                })
                .addOnProgressListener(taskSnapshot -> {
                    double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    pd.setMessage("Progress:" + (int) progressPercent + "%");
                });
    }
}