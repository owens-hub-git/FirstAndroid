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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        inputSpinner = findViewById(R.id.inputSpinner);
        outputSpinner = findViewById(R.id.outputSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultView = findViewById(R.id.resultView);

        // Set up adapter using predefined string-array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.conversion_units, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(adapter);
        outputSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(view -> convert());
    }

    private void convert() {
        String inputText = inputValue.getText().toString();
        String fromUnit = inputSpinner.getSelectedItem().toString();
        String toUnit = outputSpinner.getSelectedItem().toString();

        if (inputText.isEmpty()) {
            resultView.setText("Please enter a value");
            return;
        }

        double input;
        //simple try catch block to for valid input
        try {
            input = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            resultView.setText("Invalid number");
            return;
        }

        double result = performConversion(input, fromUnit, toUnit);
        if (result == input && !fromUnit.equals(toUnit)) {
            resultView.setText("Invalid conversion");
        } else {
            resultView.setText(String.format("Result: %.2f %s", result, toUnit));
        }
    }

    private double performConversion(double value, String fromUnit, String toUnit) {
        //Temperature conversions
        if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit"))
            return (value * 1.8) + 32;
        if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius"))
            return (value - 32) / 1.8;
        if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin"))
            return value + 273.15;
        if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius"))
            return value - 273.15;

        //Length
        if (fromUnit.equals("Inches") && toUnit.equals("Centimeters"))
            return value * 2.54;
        if (fromUnit.equals("Feet") && toUnit.equals("Centimeters"))
            return value * 30.48;
        if (fromUnit.equals("Yards") && toUnit.equals("Centimeters"))
            return value * 91.44;
        if (fromUnit.equals("Miles") && toUnit.equals("Kilometers"))
            return value * 1.60934;

        //Weight
        if (fromUnit.equals("Pounds") && toUnit.equals("Kilograms"))
            return value * 0.453592;
        if (fromUnit.equals("Ounces") && toUnit.equals("Grams"))
            return value * 28.3495;
        if (fromUnit.equals("Tons") && toUnit.equals("Kilograms"))
            return value * 907.185;
        // If no conversion applied (or same units), return original
        return value;
    }

}
