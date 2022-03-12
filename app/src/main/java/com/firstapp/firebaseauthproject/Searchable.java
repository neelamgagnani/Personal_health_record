package com.firstapp.firebaseauthproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class Searchable extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<>();
    SpinnerDialog spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        final TextView selectedItems=(TextView)findViewById(R.id.txt);

        items.add("Dr.Raman Batra");
        items.add("Dr.Divyesh Patel");
        items.add("Dr.Yashika Sharma");
        items.add("Dr.Rudra Chauhan");
        items.add("Dr.Bhavya Aggarwal");
        items.add("Dr.Vanshika Chawla");
        items.add("Dr.Yuyutsu Vyas");
        items.add("Dr.Ragini Gajjar");
        items.add("Dr.Rishi Sanghvi");
        items.add("Dr.Aaradhna Jain");
        items.add("Dr.Radhika Goswami");
        items.add("Dr.Agasthya Shah");


        spinnerDialog=new SpinnerDialog(    Searchable.this,items,"Search Doctor","Close");// With No Animation
        spinnerDialog=new SpinnerDialog(Searchable.this,items,"Search Doctor",R.style.DialogAnimations_SmileWindow,"Close");// With 	Animation

        spinnerDialog.setCancellable(true); // for cancellable
        spinnerDialog.setShowKeyboard(false);// for open keyboard by default


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(Searchable.this, item + "  " + position+"", Toast.LENGTH_SHORT).show();
                selectedItems.setText(item + " Position: " + position);
            }
        });
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });
    }
}

