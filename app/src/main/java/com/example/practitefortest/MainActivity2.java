package com.example.practitefortest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // Napojení na layout

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Nastavení Toolbaru
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Povolení "zpět" šipky
        toolbar.setNavigationOnClickListener(v -> onBackPressed()); // Kliknutí na Toolbar = zpět

        // Převzetí dat ze spinneru a jejich zobrazení
        String selectedFromSpinner = getIntent().getStringExtra("spinnerSelection");
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Aktivita 2\nVybral jsi: " + selectedFromSpinner);
    }
}
