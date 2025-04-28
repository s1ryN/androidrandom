package com.example.practitefortest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerOperation; // Spinner pro výběr položky
    final String TAG = "MainActivity"; // TAG pro logování

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Napojení na layout

        // Přiřazení spinneru a naplnění hodnotami ze string-array
        spinnerOperation = findViewById(R.id.spinnerLayout);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerVyber, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperation.setAdapter(adapter);

        Log.d(TAG, "onCreate()"); // Log pro životní cyklus
    }

    // Metoda na zjištění, zda je číslo sudé/liché
    public void calculate(View view) {
        EditText input = findViewById(R.id.poleCiso);
        TextView output = findViewById(R.id.poleText);
        try {
            int number = Integer.parseInt(input.getText().toString());
            String result = (number % 2 == 0) ? number + " je sudé" : number + " je liché";
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show(); // Výsledek v toastu
            output.setText(result); // Výsledek v TextView
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Neplatné číslo", Toast.LENGTH_SHORT).show(); // Chyba vstupu
        }
    }

    // Metoda pro zobrazení aktuálně vybrané položky ze spinneru
    public void ukaz(View view) {
        String selected = spinnerOperation.getSelectedItem().toString();
        Toast.makeText(this, "Vybral jsi: " + selected, Toast.LENGTH_SHORT).show();
    }

    // Přechod do druhé aktivity s přenosem vybrané položky ze spinneru
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("spinnerSelection", spinnerOperation.getSelectedItem().toString());
        startActivity(intent);
    }

    // Otevření telefonní aplikace s přednastaveným číslem
    public void callSomeone(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+420123456789"));
        startActivity(i);
    }

    // Otevření emailového klienta s předvyplněnými daty
    public void sendEmail(View view) {
        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:example@email.com"));
        email.putExtra(Intent.EXTRA_SUBJECT, "Předmět");
        email.putExtra(Intent.EXTRA_TEXT, "Text emailu...");
        startActivity(Intent.createChooser(email, "Vyber Email Aplikaci:"));
    }

    // Otevření webového prohlížeče
    public void openWebsite(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"));
        startActivity(browserIntent);
    }

    // Přechod do kvadratické kalkulačky
    public void openQuadraticActivity(View view) {
        startActivity(new Intent(this, QuadraticActivity.class));
    }

    // Životní cyklus aktivity pro logování
    @Override protected void onStart() { super.onStart(); Log.d(TAG, "onStart()"); }
    @Override protected void onResume() { super.onResume(); Log.d(TAG, "onResume()"); }
    @Override protected void onPause() { super.onPause(); Log.d(TAG, "onPause()"); }
    @Override protected void onStop() { super.onStop(); Log.d(TAG, "onStop()"); }
    @Override protected void onRestart() { super.onRestart(); Log.d(TAG, "onRestart()"); }
    @Override protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy()"); }
}
