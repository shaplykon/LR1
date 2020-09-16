package com.example.lr1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class DataModel extends ViewModel {
    private MutableLiveData<String> initalData;
    private MutableLiveData<String> convertedData;
    private MutableLiveData<Integer> initalSpinnerIndex;
    private MutableLiveData<Integer> convertedSpinnerIndex;
    private MutableLiveData<Integer> categorySpinnerIndex;




}
