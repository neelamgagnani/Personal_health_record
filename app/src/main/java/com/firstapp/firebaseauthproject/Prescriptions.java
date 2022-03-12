package com.firstapp.firebaseauthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class Prescriptions extends AppCompatActivity {
    private ImageView img;
    private static final int PICK_IMAGE=1;
    Uri imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);
        img=(ImageView) findViewById(R.id.img1);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photos = new Intent();
                photos.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(photos,"Select Prescription"),PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageuri = data.getData();
            try {
                Bitmap bitmap =MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                img.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}