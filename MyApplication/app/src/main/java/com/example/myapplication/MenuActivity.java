package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView welcomeTextView = (TextView)findViewById(R.id.welcomeTextView);
        String name = getIntent().getExtras().getString("name");
        String username = getIntent().getExtras().getString("surname");
        welcomeTextView.setText("You are welcome, " + name + " " + username);
    }
    public final static String THIEF = "";
    public void checkBoxOnClick(View view){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(THIEF, "Box CHECKED");
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void switchOnClick(View view){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(THIEF, "Switch CHECKED");
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    protected void onStart() {
        super.onStart();
    }
}