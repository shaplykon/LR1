package com.example.lr1;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

import static androidx.core.content.ContextCompat.getSystemService;

public class DataFragment extends Fragment {
    EditText initalTextView;
    EditText convertedTextView;
    Converter converter;
    Spinner initalSpinner;
    Spinner convertedSpinner;
    Spinner categorySpinner;

    String[] categories = {"Distance", "Weight", "Time"};
    String[] distanceUnits = {"Meter", "Inch", "Centimeter"};
    String[] weightUnits = {"Gram", "Kilogram", "Centner"};
    String[] timeUnits = {"Second", "Hour", "Minute"};

    DataModel dataModel = null;
    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        dataModel = ViewModelProviders.of((FragmentActivity) Objects.requireNonNull(getContext())).get(DataModel.class);

        initalTextView = view.findViewById(R.id.inintalTextView);
        convertedTextView = view.findViewById(R.id.convertedTextView);
        converter = new Converter();
        initalSpinner = view.findViewById(R.id.initalSpinner);
        convertedSpinner = view.findViewById(R.id.convertedSpinner);
        categorySpinner = view.findViewById(R.id.categorySpinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);


        final AdapterView.OnItemSelectedListener categoryItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                String item = (String) parent.getItemAtPosition(index);

                ArrayAdapter<String> initalAdapter;
                ArrayAdapter<String> convertedAdapter;

                switch (item) {
                    case "Weight":
                        initalAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, weightUnits);
                        convertedAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item);
                        break;
                    case "Time":
                        initalAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, timeUnits);
                        convertedAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, timeUnits);
                        break;
                    default:
                        initalAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, distanceUnits);
                        convertedAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, distanceUnits);
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


        initalTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (initalTextView.getText() != null && !initalTextView.getText().equals("")) {
                    int dotAmount = 0;
                    for (int k = 0; k < initalTextView.getText().toString().length(); k++) {
                        if (initalTextView.getText().toString().charAt(k) == '.') {
                            dotAmount++;
                        }
                    }
                    if (dotAmount > 1) {
                        String buffer = initalTextView.getText().toString().substring(0, initalTextView.getText().toString().length() - 1);
                        initalTextView.setText(buffer);
                    }
                    String initalData = initalTextView.getText().toString();
                    String initalUnit = initalSpinner.getSelectedItem().toString();
                    String convertedUnit = convertedSpinner.getSelectedItem().toString();
                    try {
                        convertedTextView.setText(converter.Convert(initalData, initalUnit, convertedUnit));
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




        ImageButton switchButton = view.findViewById(R.id.switchButton);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int initalBufferIndex = initalSpinner.getSelectedItemPosition();
                int convertedBufferIndex = convertedSpinner.getSelectedItemPosition();
                initalSpinner.setSelection(convertedBufferIndex, true);
                convertedSpinner.setSelection(initalBufferIndex, true);

                String initalTextBuffer = initalTextView.getText().toString();
                initalTextView.setText(convertedTextView.getText().toString());
                convertedTextView.setText(initalTextBuffer);
            }
        });

        final ImageButton initalCopyButton = view.findViewById(R.id.copyInitalButton);
        final ImageButton convertedCopyButton = view.findViewById(R.id.copyConvertedButton);

        View.OnClickListener CopyButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard =getSystemService(getContext()
                        , ClipboardManager.class);
                ClipData clip;
                String text;
                if (view.getId() == initalCopyButton.getId()) {
                    text = initalTextView.getText().toString();
                } else {
                    text = convertedTextView.getText().toString();
                }
                clip = ClipData.newPlainText("text", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Text copied", Toast.LENGTH_SHORT).show();
            }
        };
        initalCopyButton.setOnClickListener(CopyButtonClick);
        convertedCopyButton.setOnClickListener(CopyButtonClick);

        return view;
    }

    public void setData(String symbol) {
        if (symbol.equals(".")) {
            initalTextView.append(".");
        } else if (symbol.equals("-1")) {
            if (initalTextView.getText().length() >= 1) {
                initalTextView.setText(initalTextView.getText().subSequence(0, initalTextView.getText().length() - 1));
            }
        } else {
            initalTextView.append(symbol);
        }
    }


}