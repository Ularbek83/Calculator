package com.geeks.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private Double firstValue, secondValue, sum;
    private Boolean isOperationClic = false;
    private String operation;
    private Boolean isProcentClicked = false;
    private Boolean isOperationClicked = false;

    // 12 + 21 = 33
    // a + b = ab

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //      EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        textView = findViewById(R.id.text_view);
    }

    public void onNumberClic(View view) {
        String text = ((MaterialButton) view).getText().toString();
        if (text.equals("AC")) {
            textView.setText("0");

            firstValue = 0.0;
            secondValue = 0.0;
            isProcentClicked = false;
            isOperationClicked = false;
        } else if (text.equals("+/-")) {
            if (!textView.getText().toString().equals("0")) {
                Double value = Double.valueOf(textView.getText().toString()) * -1;
                textView.setText(value.toString());
            }
        } else if (text.equals(".")) {
            textView.append(text);
        } else if (textView.getText().toString().equals("0") || isOperationClic) {
            textView.setText(text);
        } else {
            textView.append(text);
        }
        isOperationClic = false;
    }

    public void onOperationClic(View view) {
        String textButton = ((Button) view).getText().toString();
        switch (textButton) {
            case "+":
                firstValue = Double.valueOf((textView.getText().toString()));
                operation = "+";
                isOperationClicked = true;
                Log.e("ololo", "onOperationClic: " + isOperationClic);
                break;
            case "x":
                firstValue = Double.valueOf((textView.getText().toString()));
                operation = "x";
                isOperationClicked = true;
                break;
            case "/":
                firstValue = Double.valueOf((textView.getText().toString()));
                operation = "/";
                isOperationClicked = true;
                break;
            case "-":
                firstValue = Double.valueOf((textView.getText().toString()));
                operation = "-";
                isOperationClicked = true;
                break;
            case "%":
                if (isOperationClicked != true) {
                    Log.e("ololo", "onOperationClic: " + isOperationClic
                            + "  " + isProcentClicked);
                    firstValue = Double.valueOf(textView.getText().toString());
                    sum = firstValue / 100;
                    textView.setText(sum.toString());
                } else {
                    isProcentClicked = true;
                }
                break;
            case "=":
                secondValue = Double.valueOf((textView.getText().toString()));
                switch (operation) {
                    case "+":
                        if (isProcentClicked) {
                            sum = firstValue + secondValue * (firstValue / 100);
                        } else {
                            sum = firstValue + secondValue;
                        }
                        textView.setText(sum.toString());
                        break;
                    case "x":
                        if (isProcentClicked) {
                            sum = firstValue * (secondValue / 100);
                        } else {
                            sum = firstValue * secondValue;
                        }
                        textView.setText(sum.toString());
                        break;
                    case "/":
                        if (isProcentClicked) {
                            sum = firstValue / (secondValue / 100);
                        } else {
                            if (secondValue != 0) {
                                sum = firstValue / secondValue;
                            } else {
                                textView.setText("Нельзя делить на ноль");
                            }
                        }
                        textView.setText(sum.toString());
                        break;
                    case "-":
                        if (isProcentClicked) {
                            sum = firstValue - (firstValue / 100) * secondValue;
                        } else {
                            sum = firstValue - secondValue;
                        }
                        textView.setText(sum.toString());
                        break;

                }
                break;
        }
        isOperationClic = true;
    }
}
