package com.example.lr1;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        dataModel = ViewModelProviders.of((FragmentActivity) Objects.requireNonNull(getContext())).get(DataModel.class);

        initalTextView = view.findViewById(R.id.inintalTextView);
        convertedTextView = view.findViewById(R.id.convertedTextView);
        initalSpinner = view.findViewById(R.id.initalSpinner);
        convertedSpinner = view.findViewById(R.id.convertedSpinner);
        categorySpinner = view.findViewById(R.id.categorySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        dataModel.categorySpinnerValue.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ArrayAdapter<String> initalAdapter;
                ArrayAdapter<String> convertedAdapter;

                switch (s) {
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
        });

        dataModel.initalSpinnerValue.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ArrayAdapter<String> viewAdapter = (ArrayAdapter<String>) initalSpinner.getAdapter();
                initalSpinner.setSelection(viewAdapter.getPosition(s));
            }
        });

        dataModel.convertedSpinnerValue.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ArrayAdapter<String> viewAdapter = (ArrayAdapter<String>) convertedSpinner.getAdapter();
                convertedSpinner.setSelection(viewAdapter.getPosition(s));
            }
        });


        ImageButton switchButton = view.findViewById(R.id.switchButton);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buffer = dataModel.initalData.getValue();
                dataModel.initalData.setValue(dataModel.convertedData.getValue());
                dataModel.convertedData.setValue(buffer);
                buffer = dataModel.initalSpinnerValue.getValue();
                dataModel.initalSpinnerValue.setValue(dataModel.convertedSpinnerValue.getValue());
                dataModel.convertedSpinnerValue.setValue(buffer);

                dataModel.convertInitalValue();
            }
        });

        final ImageButton initalCopyButton = view.findViewById(R.id.copyInitalButton);
        final ImageButton convertedCopyButton = view.findViewById(R.id.copyConvertedButton);

        View.OnClickListener CopyButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = getSystemService(Objects.requireNonNull(getContext())
                        , ClipboardManager.class);
                ClipData clip;
                String text;
                if (view.getId() == initalCopyButton.getId()) {
                    text = initalTextView.getText().toString();
                } else {
                    text = convertedTextView.getText().toString();
                }
                clip = ClipData.newPlainText("text", text);
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Text copied", Toast.LENGTH_SHORT).show();
            }
        };
        initalCopyButton.setOnClickListener(CopyButtonClick);
        convertedCopyButton.setOnClickListener(CopyButtonClick);


        // Observe data changes
        dataModel.initalData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                initalTextView.setText(s);
            }
        });

        dataModel.convertedData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                convertedTextView.setText(s);
            }
        });

        initalSpinner.setOnItemSelectedListener(onItemSelectedListener);
        convertedSpinner.setOnItemSelectedListener(onItemSelectedListener);
        categorySpinner.setOnItemSelectedListener(categoryItemSelectedListener);

        return view;
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            dataModel.initalSpinnerValue.setValue(initalSpinner.getSelectedItem().toString());
            dataModel.convertedSpinnerValue.setValue(convertedSpinner.getSelectedItem().toString());
            dataModel.convertInitalValue();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    AdapterView.OnItemSelectedListener categoryItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            dataModel.setNewCategory(categorySpinner.getItemAtPosition(i).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


}