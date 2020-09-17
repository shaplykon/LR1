package com.example.lr1;

import android.annotation.SuppressLint;
import android.content.Context;


public  class Converter {
    String[] distanceUnits = {"Inch", "Meter", "Centimeter"};
    String[] weightUnits = {"Gram", "Kilogram", "Centner"};
    String[] timeUnits = {"Second", "Minute", "Hour"};


    public String Convert(String inintalData, String initalUnit, String convertedUnit)  {
        for (String str:
             distanceUnits) {
            if(initalUnit.equals(str)){
                return DistanceConverter(inintalData, initalUnit, convertedUnit);
            }
        }
        for (String str:
                weightUnits) {
            if(initalUnit.equals(str)){
                return WeightConverter(inintalData, initalUnit, convertedUnit);
            }
        }

        for (String str:
                timeUnits) {
            if(initalUnit.equals(str)){
                return TimeConverter(inintalData, initalUnit, convertedUnit);
            }
        }
        return "";

    }

    @SuppressLint("DefaultLocale")
    private String WeightConverter(String initalData, String initalUnit, String convertedUnit){
        double inital = Double.parseDouble(initalData);
        double converted = 0;
        switch (initalUnit) {
            case "Centner":
                if (convertedUnit.equals("Kilogram")) {
                    converted = inital * 100;
                } else if (convertedUnit.equals("Gram")) {
                    converted = inital * 100000;
                }
                else{
                    converted = inital;
                }
                break;
            case "Kilogram":
                if (convertedUnit.equals("Centner")) {
                    converted = inital * 0.01;
                } else if (convertedUnit.equals("Gram")) {
                    converted = inital * 1000;
                }
                else{
                    converted = inital;
                }
                break;
            case "Gram":
                if (convertedUnit.equals("Centner")) {
                    converted = inital * 0.00001;
                } else if (convertedUnit.equals("Kilogram")) {
                    converted = inital * 0.001;
                }
                else{
                    converted = inital;
                }
                break;
        }
        return String.valueOf(converted);
    }


    @SuppressLint("DefaultLocale")
    private String DistanceConverter(String initalData, String initalUnit, String convertedUnit){
        double inital = Double.parseDouble(initalData);
        double converted = 0;
        switch (initalUnit) {
            case "Inch":
                if (convertedUnit.equals("Meter")) {
                    converted = inital * 0.0254;
                } else if (convertedUnit.equals("Centimeter")) {
                    converted = inital * 2.54;
                }
                else{
                    converted = inital;
                }
                break;
            case "Meter":
                if (convertedUnit.equals("Inch")) {
                    converted = inital * 39.37007874;
                } else if (convertedUnit.equals("Centimeter")) {
                    converted = inital * 100;
                }
                else{
                    converted = inital;
                }
                break;
            case "Centimeter":
                if (convertedUnit.equals("Inch")) {
                    converted = inital * 0.3937007874;
                } else if (convertedUnit.equals("Meter")) {
                    converted = inital * 0.01;
                }
                else{
                    converted = inital;
                }
                break;
        }



        return String.valueOf(converted);
    }

    @SuppressLint("DefaultLocale")
    private String TimeConverter(String initalData, String initalUnit, String convertedUnit) {
        double inital = Double.parseDouble(initalData);
        double converted = 0;
        switch (initalUnit) {
            case "Second":
                if (convertedUnit.equals("Minute")) {
                    converted = inital * 0.017;
                } else if (convertedUnit.equals("Hour")) {
                    converted = inital * 0.000277777778;
                }
                else{
                    converted = inital;
                }
                break;
            case "Minute":
                if (convertedUnit.equals("Second")) {
                    converted = inital * 60;
                } else if (convertedUnit.equals("Hour")) {
                    converted = inital * 0.017;
                }
                else{
                    converted = inital;
                }
                break;
            case "Hour":
                if (convertedUnit.equals("Minute")) {
                    converted = inital * 60;
                } else if (convertedUnit.equals("Second")) {
                    converted = inital * 3600;
                }
                else{
                    converted = inital;
                }
                break;
        }

        return String.valueOf(converted);
    }

}

