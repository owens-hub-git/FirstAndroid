package com.example.firstandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner inputSpinner, outputSpinner;
    Button convertButton;
    TextView resultView;

    private final String[] temperatureUnits = {"Celsius", "Fahrenheit", "Kelvin"};
    private final String[] weightUnits = {"Grams", "Kilograms", "Pounds", "Ounces"};
    private final String[] lengthUnits = {"Millimeters", "Centimeters", "Meters", "Kilometers", "Inches", "Feet", "Yards", "Miles"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize UI components
        inputValue = findViewById(R.id.inputValue);
        inputSpinner = findViewById(R.id.inputSpinner);
        outputSpinner = findViewById(R.id.outputSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultView = findViewById(R.id.resultView);

        //both spinners using the same unit list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(adapter);
        outputSpinner.setAdapter(adapter);

        //convert button click
        convertButton.setOnClickListener(view -> convert());
    }

    private void convert() {
        String inputUnit = inputSpinner.getSelectedItem().toString();
        String outputUnit = outputSpinner.getSelectedItem().toString();
        String inputText = inputValue.getText().toString();

        if (inputText.isEmpty()) {
            resultView.setText("Please enter a value");
            return;
        }

        double input = Double.parseDouble(inputText);

        //validate if the selected units belong to the same category
        if (!isSameCategory(inputUnit, outputUnit)) {
            resultView.setText("Incorrect conversion");
            return;
        }

        //perform the conversion
        double result = performConversion(input, inputUnit, outputUnit);
        resultView.setText(String.format("%.2f %s", result, outputUnit));
    }

    private boolean isSameCategory(String unit1, String unit2) {
        return (belongsToCategory(unit1, temperatureUnits) && belongsToCategory(unit2, temperatureUnits)) ||
                (belongsToCategory(unit1, weightUnits) && belongsToCategory(unit2, weightUnits)) ||
                (belongsToCategory(unit1, lengthUnits) && belongsToCategory(unit2, lengthUnits));
    }

    private boolean belongsToCategory(String unit, String[] category) {
        for (String validUnit : category)//basically foreach loop like in c#
            {
            if (validUnit.equals(unit)) return true;
        }
        return false;
    }


    //https://www.unitconverters.net/length-converter.html for conversion values
    private double performConversion(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            //temperature
            case "Celsius":
                if (toUnit.equals("Fahrenheit")) return (value * 9 / 5) + 32;
                if (toUnit.equals("Kelvin")) return value + 273.15;
                break;
            case "Fahrenheit":
                if (toUnit.equals("Celsius")) return (value - 32) * 5 / 9;
                if (toUnit.equals("Kelvin")) return (value - 32) * 5 / 9 + 273.15;
                break;
            case "Kelvin":
                if (toUnit.equals("Celsius")) return value - 273.15;
                if (toUnit.equals("Fahrenheit")) return (value - 273.15) * 9 / 5 + 32;
                break;

            //weight
            case "Grams":
                if (toUnit.equals("Kilograms")) return value / 1000;
                if (toUnit.equals("Pounds")) return value * 0.00220462;
                if (toUnit.equals("Ounces")) return value * 0.035274;
                break;
            case "Kilograms":
                if (toUnit.equals("Grams")) return value * 1000;
                if (toUnit.equals("Pounds")) return value * 2.20462;
                if (toUnit.equals("Ounces")) return value * 35.274;
                break;
            case "Pounds":
                if (toUnit.equals("Grams")) return value / 0.00220462;
                if (toUnit.equals("Kilograms")) return value / 2.20462;
                if (toUnit.equals("Ounces")) return value * 16;
                break;
            case "Ounces":
                if (toUnit.equals("Grams")) return value / 0.035274;
                if (toUnit.equals("Kilograms")) return value / 35.274;
                if (toUnit.equals("Pounds")) return value / 16;
                break;

            //length
            case "Millimeters":
                if (toUnit.equals("Centimeters")) return value / 10;
                if (toUnit.equals("Meters")) return value / 1000;
                if (toUnit.equals("Kilometers")) return value / 1_000_000;
                if (toUnit.equals("Inches")) return value * 0.0393701;
                if (toUnit.equals("Feet")) return value * 0.00328084;
                if (toUnit.equals("Yards")) return value * 0.00109361;
                if (toUnit.equals("Miles")) return value * 6.2137e-7;
                break;
            case "Centimeters":
                if (toUnit.equals("Millimeters")) return value * 10;
                if (toUnit.equals("Meters")) return value / 100;
                if (toUnit.equals("Kilometers")) return value / 100_000;
                if (toUnit.equals("Inches")) return value * 0.393701;
                if (toUnit.equals("Feet")) return value * 0.0328084;
                if (toUnit.equals("Yards")) return value * 0.0109361;
                if (toUnit.equals("Miles")) return value * 6.2137e-6;
                break;
            case "Meters":
                if (toUnit.equals("Millimeters")) return value * 1000;
                if (toUnit.equals("Centimeters")) return value * 100;
                if (toUnit.equals("Kilometers")) return value / 1000;
                if (toUnit.equals("Inches")) return value * 39.3701;
                if (toUnit.equals("Feet")) return value * 3.28084;
                if (toUnit.equals("Yards")) return value * 1.09361;
                if (toUnit.equals("Miles")) return value * 0.000621371;
                break;
        }
        return value;
    }
}
