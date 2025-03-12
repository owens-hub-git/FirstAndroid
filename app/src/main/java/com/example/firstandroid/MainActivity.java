package com.example.firstandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText celciusInput;
    Button buttonConvert;
    TextView resultView;

    double celcius;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        celciusInput = findViewById(R.id.celciusInput);
        buttonConvert = findViewById(R.id.buttonConvert);
        resultView = findViewById(R.id.resultView);

        buttonConvert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String inputStr = celciusInput.getText().toString();

                if(inputStr.isEmpty()){
                    resultView.setText("Enter a value");
                }

                try{
                    celcius = Double.parseDouble(inputStr);
                }
                catch (NumberFormatException e){
                    resultView.setText("Invalid Number");

                }
                //conversion logics
                double fahrenheit = (celcius * 1.8) + 32;

                resultView.setText(String.format("Result: %.2f F", fahrenheit));
            }
        });

    }
}