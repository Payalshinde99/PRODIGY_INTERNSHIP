package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView display;
    String expression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setText("0");
    }

    // Digits 0–9
    public void onDigitClick(View view) {
        Button button = (Button) view;
        expression += button.getText().toString();
        display.setText(expression);
    }

    // Operators + − × ÷
    public void onOperatorClick(View view) {
        Button button = (Button) view;
        String op = button.getText().toString();

        if (!expression.isEmpty()) {
            expression += op;
            display.setText(expression);
        }
    }

    // Equals =
    public void onEqual(View view) {
        try {
            double result = evaluateExpression(expression);

            // Remove .0 if result is whole number
            if (result == (int) result) {
                display.setText(String.valueOf((int) result));
                expression = String.valueOf((int) result);
            } else {
                display.setText(String.valueOf(result));
                expression = String.valueOf(result);
            }

        } catch (Exception e) {
            display.setText("Error");
            expression = "";
        }
    }

    // Clear C
    public void onClear(View view) {
        expression = "";
        display.setText("0");
    }

    // Expression evaluator (single operator)
    private double evaluateExpression(String exp) {

        exp = exp.replace("×", "*")
                .replace("÷", "/")
                .replace("−", "-");

        if (exp.contains("+")) {
            String[] parts = exp.split("\\+");
            return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
        }
        else if (exp.contains("-")) {
            String[] parts = exp.split("-");
            return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
        }
        else if (exp.contains("*")) {
            String[] parts = exp.split("\\*");
            return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
        }
        else if (exp.contains("/")) {
            String[] parts = exp.split("/");
            return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        }

        return 0;
    }
}
