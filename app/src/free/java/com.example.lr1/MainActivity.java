package com.example.lr1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String[] categories = {"Distance", "Weight", "Time"};
    String[] distanceUnits = {"Inch", "Meter", "Centimeter"};
    String[] weightUnits = {"Gram", "Kilogram", "Centner"};
    String[] timeUnits = {"Second", "Minute", "Hour"};


    static final String STATE_INITAL_TEXT = "inital_data";
    static final String STATE_INITAL_SPINNER = "inital_spinner";
    static final String STATE_CONVERTED_SPINNER = "converted_spinner";
    static final String STATE_UNIT = "unit";


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
        versionNameTextView.setText("Application version: " + BuildConfig.VERSION_NAME);

        final Spinner categorySpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        final Converter converter = new Converter();

        initalTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!initalTextView.getText().equals("")) {
                    String initalData = initalTextView.getText().toString();
                    String initalUnit = initalSpinner.getSelectedItem().toString();
                    String convertedUnit = convertedSpinner.getSelectedItem().toString();

                    int dotAmount = 0;

                    for (int k = 0; k < initalData.length(); k++) {
                        if (initalData.charAt(k) == '.') {
                            dotAmount++;
                        }
                    }

                    if (dotAmount > 1) {
                        initalData = initalData.substring(0, initalData.length() - 1);
                        initalTextView.setText(initalData);
                    }

                    try {
                        convertedTextView.setText(converter.Convert(initalData, initalUnit, convertedUnit, MainActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (initalTextView.getText().toString().length() == 0) {
                    convertedTextView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        Button button1 = findViewById(R.id.button1);
        button1.setTag(1);

        Button button2 = findViewById(R.id.button2);
        button2.setTag(2);

        Button button3 = findViewById(R.id.button3);
        button3.setTag(3);

        Button button4 = findViewById(R.id.button4);
        button4.setTag(4);

        Button button5 = findViewById(R.id.button5);
        button5.setTag(5);

        Button button6 = findViewById(R.id.button6);
        button6.setTag(6);

        Button button7 = findViewById(R.id.button7);
        button7.setTag(7);

        Button button8 = findViewById(R.id.button8);
        button8.setTag(8);

        Button button9 = findViewById(R.id.button9);
        button9.setTag(9);

        Button button0 = findViewById(R.id.button0);
        button0.setTag(0);

        Button buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setTag('.');

        Button buttonBackspace = findViewById(R.id.buttonBackspace);
        buttonBackspace.setTag("-1");

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getTag().equals(".")) {
                    initalTextView.append(".");
                } else if (v.getTag().toString().equals("-1")) {
                    if (initalTextView.getText().length() >= 1) {
                        initalTextView.setText(initalTextView.getText().subSequence(0, initalTextView.getText().length() - 1));
                    }
                } else {
                    initalTextView.append(v.getTag().toString());
                }
            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        button0.setOnClickListener(onClickListener);
        buttonBackspace.setOnClickListener(onClickListener);
        buttonDot.setOnClickListener(onClickListener);

        final AdapterView.OnItemSelectedListener categoryItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                String item = (String) parent.getItemAtPosition(index);

                ArrayAdapter<String> initalAdapter;
                ArrayAdapter<String> convertedAdapter;

                switch (item) {
                    case "Weight":
                        initalAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, weightUnits);
                        convertedAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item);
                        break;
                    case "Time":
                        initalAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, timeUnits);
                        convertedAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, timeUnits);
                        break;
                    default:
                        initalAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, distanceUnits);
                        convertedAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, distanceUnits);
                        break;
                }
                initalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                initalSpinner.setAdapter(initalAdapter);
                convertedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                convertedSpinner.setAdapter(initalAdapter);
                convertedSpinner.setSelection(2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        categorySpinner.setOnItemSelectedListener(categoryItemSelectedListener);

        final AdapterView.OnItemSelectedListener UnitItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                if (!initalTextView.getText().toString().equals(""))
                    initalTextView.setText(initalTextView.getText().subSequence(0, initalTextView.getText().length()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        initalSpinner.setOnItemSelectedListener(UnitItemSelectedListener);
        convertedSpinner.setOnItemSelectedListener(UnitItemSelectedListener);
    }

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
}

