package com.example.practitefortest;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuadraticActivity extends AppCompatActivity {

    private EditText inputA, inputB, inputC, inputD; // Vstupy pro koeficienty
    private Spinner spinnerOperator; // Spinner pro výběr operátoru
    private TextView resultText; // Výsledek

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3); // Layout

        // Přiřazení prvků
        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        inputC = findViewById(R.id.inputC);
        inputD = findViewById(R.id.inputD);
        spinnerOperator = findViewById(R.id.spinnerOperator);
        resultText = findViewById(R.id.resultText);
        Button calculateButton = findViewById(R.id.calculateButton);

        // Naplnění spinneru
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperator.setAdapter(adapter);

        calculateButton.setOnClickListener(v -> calculateQuadratic()); // Tlačítko na výpočet
    }

    // Převod vstupu na číslo s pravidly pro prázdné hodnoty
    private double parseInput(String text, boolean isBeforeX) {
        if (text.isEmpty()) return isBeforeX ? 1 : 0; // Prázdné = 1 (před x), jinak 0
        if (text.equals("-")) return -1; // Pokud pouze "-", tak -1
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show(); // Chyba
            return 0;
        }
    }

    // Výpočet rovnice a její řešení
    private void calculateQuadratic() {
        double a = parseInput(inputA.getText().toString(), true);
        double b = parseInput(inputB.getText().toString(), true);
        double c = parseInput(inputC.getText().toString(), false);
        double d = parseInput(inputD.getText().toString(), false);

        String operator = spinnerOperator.getSelectedItem().toString();
        String equation = a + "x² + " + b + "x + " + c + " " + operator + " " + d;
        String solution = solveQuadratic(a, b, c, d, operator);

        resultText.setText("Equation: " + equation + "\nSolution: " + solution); // Výsledek
    }

    // Výpočet diskriminantu a kořenů rovnice
    private String solveQuadratic(double a, double b, double c, double d, String operator) {
        double newC = c - d; // Převod na levou stranu
        double discriminant = (b * b) - (4 * a * newC);
        // Kontrola hodnoty diskriminantu
        if (discriminant < 0) {
            return "Žádné reálné řešení."; // Pokud je diskriminant záporný, nejsou reálná řešení
        } else if (discriminant == 0) {
            double x = -b / (2 * a); // Jedno dvojnásobné řešení
            return "x = " + x; // Vrátí jedno řešení
        } else {
            // Dvě reálná řešení
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return "x₁ = " + x1 + ", x₂ = " + x2; // Vrátí obě řešení
        }
    }
}
