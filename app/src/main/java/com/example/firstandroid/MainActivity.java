package com.example.firstandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText celciusInput;
    Button buttonConvert;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        celciusInput = findViewById(R.id.celciusInput);
        buttonConvert = findViewById(R.id.buttonConvert);
        resultView = findViewById(R.id.resultView);

        buttonConvert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String inputString = celciusInput.getText().toString();

//                    if (inputString.isEmpty()) {
//                    resultText.setText("Please enter some value!");
//                  }
//                  Toast.makeText(MainActivity.this, inputString, Toast.LENGTH_SHORT).show();

                    double celcius = Double.parseDouble(inputString);

                    //conversion logics
                    double fahrenheit = (celcius * 1.8) + 32;

                    resultView.setText(String.format("Result: %.2f F", fahrenheit));
            }
        });
    }
}
