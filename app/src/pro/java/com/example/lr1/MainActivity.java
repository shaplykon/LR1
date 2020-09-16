package com.example.lr1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements KeyboardFragment.OnFragmentInteractionListener {
    static final String STATE_INITAL_TEXT = "inital_data";
    static final String STATE_INITAL_SPINNER = "inital_spinner";
    static final String STATE_CONVERTED_SPINNER = "converted_spinner";
    static final String STATE_UNIT = "unit";

    String[] categories = {"Distance", "Weight", "Time"};
    String[] distanceUnits = {"Meter", "Inch", "Centimeter"};
    String[] weightUnits = {"Gram", "Kilogram", "Centner"};
    String[] timeUnits = {"Second", "Hour", "Minute"};

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView initalTextView = findViewById(R.id.inintalTextView);
        final TextView convertedTextView = findViewById(R.id.convertedTextView);
        final Spinner initalSpinner = findViewById(R.id.initalSpinner);
        final Spinner convertedSpinner = findViewById(R.id.convertedSpinner);

        if (savedInstanceState != null) {

            switch (Objects.requireNonNull(savedInstanceState.getString(STATE_UNIT))) {
                case "distance": {
                    ArrayAdapter<String> inintalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distanceUnits);
                    inintalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    initalSpinner.setAdapter(inintalAdapter);

                    ArrayAdapter<String> convertedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distanceUnits);
                    convertedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertedSpinner.setAdapter(convertedAdapter);

                    break;
                }
                case "time": {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeUnits);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    initalSpinner.setAdapter(adapter);

                    ArrayAdapter<String> convertedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeUnits);
                    convertedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertedSpinner.setAdapter(convertedAdapter);

                    break;
                }
                case "weight": {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weightUnits);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    initalSpinner.setAdapter(adapter);

                    ArrayAdapter<String> convertedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weightUnits);
                    convertedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertedSpinner.setAdapter(convertedAdapter);

                    break;
                }
            }
            initalSpinner.setSelection(savedInstanceState.getInt(STATE_INITAL_SPINNER));
            convertedSpinner.setSelection(savedInstanceState.getInt(STATE_CONVERTED_SPINNER));
            initalTextView.setText(savedInstanceState.getString(STATE_INITAL_TEXT));
        }


        TextView versionNameTextView = findViewById(R.id.versionNameTextView);
        versionNameTextView.setText("Application version: " + com.example.lr1.BuildConfig.VERSION_NAME);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        final Spinner initalSpinner = findViewById(R.id.initalSpinner);
        final Spinner convertedSpinner = findViewById(R.id.convertedSpinner);
        TextView initalTextView = findViewById(R.id.inintalTextView);

        if (Arrays.asList(distanceUnits).contains(initalSpinner.getSelectedItem().toString())) {
            outState.putString(STATE_UNIT, "distance");
        } else if (Arrays.asList(timeUnits).contains(initalSpinner.getSelectedItem().toString())) {
            outState.putString(STATE_UNIT, "time");
        } else if (Arrays.asList(weightUnits).contains(initalSpinner.getSelectedItem().toString())) {
            outState.putString(STATE_UNIT, "weight");
        }
        outState.putString(STATE_INITAL_TEXT, initalTextView.getText().toString());
        outState.putInt(STATE_INITAL_SPINNER, initalSpinner.getSelectedItemPosition());
        outState.putInt(STATE_CONVERTED_SPINNER, convertedSpinner.getSelectedItemPosition());
    }

    @Override
    public void onFragmentInteraction(String symbol) {
        DataFragment dataFragment = (DataFragment)getSupportFragmentManager().findFragmentById(R.id.dataFragment);
        if(dataFragment != null && dataFragment.isInLayout()){
            dataFragment.setData(symbol);
        }
    }
}