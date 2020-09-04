package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    static final private int RESULT = 0;
    public void onClickStart(View view){
        EditText nameText = (EditText)findViewById(R.id.editName);
        EditText surnameText = (EditText)findViewById(R.id.editSurname);
        if(nameText.getText().toString().length() >= 3 && surnameText.getText().toString().length() >= 3){
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("name", nameText.getText().toString());
            intent.putExtra("surname", surnameText.getText().toString());
            startActivityForResult(intent, RESULT);
        }

        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Input correct data!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView resultTextView = (TextView)findViewById(R.id.resultTextView);
        if(requestCode == RESULT){
            if(resultCode == RESULT_OK){
                resultTextView.setText(data.getStringExtra(MenuActivity.THIEF));
            }
            else {
                resultTextView.setText("Activity was closed");

            }

        }


    }
}