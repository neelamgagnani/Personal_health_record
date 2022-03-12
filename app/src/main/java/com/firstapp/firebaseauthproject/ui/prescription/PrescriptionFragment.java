package com.firstapp.firebaseauthproject.ui.prescription;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.firstapp.firebaseauthproject.R;

public class PrescriptionFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PrescriptionViewModel prescriptionViewModel = new ViewModelProvider(this).get(PrescriptionViewModel.class);
        View root = inflater.inflate(R.layout.nav_header_main, container, false);
        final TextView textView = root.findViewById(R.id.imageView);
        prescriptionViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }
}