package com.example.lr1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    DataModel dataModel;
    String[] categories = {"Distance", "Weight", "Time"};


    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        dataModel = ViewModelProviders.of(Objects.requireNonNull(this)).get(DataModel.class);
        setContentView(R.layout.activity_main);
        if (dataModel.needInitialization) {
            dataModel.setCategories(categories);
        }
        TextView versionNameTextView = findViewById(R.id.versionNameTextView);
        versionNameTextView.setText("Application version: " + com.example.lr1.BuildConfig.VERSION_NAME);


    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment.getClass().equals(KeyboardFragment.class) && fragment.isInLayout()) {

            ((KeyboardFragment) fragment).setNumButtonClickListener(dataModel);
        }
    }
}