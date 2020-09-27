package com.example.lr1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

public class DataModel extends ViewModel implements KeyboardFragment.OnNumButtonClickListener {
    MutableLiveData<String> initalData;
    MutableLiveData<String> convertedData;
    MutableLiveData<String> initalSpinnerValue;
    MutableLiveData<String> convertedSpinnerValue;
    MutableLiveData<String> categorySpinnerValue;
    String[] categories;
    boolean needInitialization = true;
    Converter converter = new Converter();

    public void initCategories(String[] units) {
        if (needInitialization) {
            this.categories = units;
            categorySpinnerValue.setValue(categories[0]);
            needInitialization = false;
        }
    }

    public void setNewCategory(String category) {
        categorySpinnerValue.setValue(category);
    }

    public DataModel() {
        initalData = new MutableLiveData<>();
        initalData.setValue("");
        convertedData = new MutableLiveData<>();
        convertedData.setValue("");
        initalSpinnerValue = new MutableLiveData<>();
        convertedSpinnerValue = new MutableLiveData<>();
        categorySpinnerValue = new MutableLiveData<>();
    }

    @Override
    public void onNumButtonClick(int number) {
        String newValue = initalData.getValue();

        switch (number) {
            case -1:
                if (newValue != null) {
                    if (!newValue.equals("")) {
                        newValue = newValue.substring(0, newValue.length() - 1);
                    } else {
                        newValue = "";
                    }
                }
                break;
            case -2:
                int dotAmount = 0;
                if (newValue != null) {
                    for (int i = 0; i < newValue.length(); i++) {
                        if (newValue.charAt(i) == '.') {
                            dotAmount += 1;
                        }
                    }

                    if (dotAmount == 0 && newValue.length() > 0) {
                        newValue += ".";
                    }

                    if (newValue.length() == 0) {
                        newValue += "0.";
                    }
                }
                break;
            default:
                newValue += String.valueOf(number);
                break;
        }
        initalData.setValue(newValue);
        convertInitalValue();
    }

    public void convertInitalValue() {
        if (!Objects.equals(initalData.getValue(), "")) {
            convertedData.setValue(converter.Convert(initalData.getValue(), initalSpinnerValue.getValue(), convertedSpinnerValue.getValue()));
        } else convertedData.setValue("");
    }
}

