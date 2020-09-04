package com.example.lr1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.TextView;
import com.example.lr1.BuildConfig;
public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();
        TextView versionNameTextView = (TextView)findViewById(R.id.versionNameTextView);
        TextView versionCodeTextView = (TextView)findViewById(R.id.versionCodeTextView);
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        versionNameTextView.setText(versionName);
        versionCodeTextView.setText(Integer.toString(versionCode));



    }
}