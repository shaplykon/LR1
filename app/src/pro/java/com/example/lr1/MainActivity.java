package com.example.lr1;
import androidx.appcompat.app.AppCompatActivity;
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
        dataModel.initCategories(categories);
        setContentView(R.layout.activity_main);
        TextView versionNameTextView = findViewById(R.id.versionNameTextView);
        versionNameTextView.setText("Application version: " + com.example.lr1.BuildConfig.VERSION_NAME);
    }
}