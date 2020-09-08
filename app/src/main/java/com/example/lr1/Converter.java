package com.example.lr1;

import androidx.appcompat.app.AppCompatActivity;

public  class Converter {
    String[] distanceUnits = {"inch", "meter", "centimeter"};
    String[] weightUnits = {"gram", "kilogram", "centner"};
    String[] currencyUnits = {"USD", "EUR", "BYN"};

    public String Convert(String inintalData, String initalUnit, String convertedUnit){
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
        return "";

    }

    private String WeightConverter(String initalData, String initalUnit, String convertedUnit){
        double inital = Double.parseDouble(initalData);
        double converted = 0;
        switch (initalUnit) {
            case "centner":
                if (convertedUnit.equals("kilogram")) {
                    converted = inital * 100;
                } else if (convertedUnit.equals("gram")) {
                    converted = inital * 100000;
                }
                break;
            case "kilogram":
                if (convertedUnit.equals("centner")) {
                    converted = inital * 0.01;
                } else if (convertedUnit.equals("gram")) {
                    converted = inital * 1000;
                }
                break;
            case "gram":
                if (convertedUnit.equals("centner")) {
                    converted = inital * 0.00001;
                } else if (convertedUnit.equals("kilogram")) {
                    converted = inital * 0.001;
                }
                break;
        }
        return String.valueOf(converted);
    }


    private String DistanceConverter(String initalData, String initalUnit, String convertedUnit){
        double inital = Double.parseDouble(initalData);
        double converted = 0;
        switch (initalUnit) {
            case "inch":
                if (convertedUnit.equals("meter")) {
                    converted = inital * 0.025;
                } else if (convertedUnit.equals("centimeter")) {
                    converted = inital * 2.54;
                }
                break;
            case "meter":
                if (convertedUnit.equals("inch")) {
                    converted = inital * 39.37;
                } else if (convertedUnit.equals("centimeter")) {
                    converted = inital * 100;
                }
                break;
            case "centimeter":
                if (convertedUnit.equals("inch")) {
                    converted = inital * 0.39;
                } else if (convertedUnit.equals("meter")) {
                    converted = inital * 0.01;
                }
                break;
        }
        return String.valueOf(converted);
    }
}

