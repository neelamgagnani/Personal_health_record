package com.firstapp.firebaseauthproject.ui.vitals;

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

public class VitalsFragment extends Fragment {

    private VitalsViewModel vitalsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vitalsViewModel =
                new ViewModelProvider(this).get(VitalsViewModel.class);
        View root = inflater.inflate(R.layout.nav_header_main, container, false);
        final TextView textView = root.findViewById(R.id.imageView);
        vitalsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}