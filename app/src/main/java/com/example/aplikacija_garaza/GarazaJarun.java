package com.example.aplikacija_garaza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GarazaJarun extends AppCompatActivity {
    Button odjavaButtonDruga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugagaraza);

        odjavaButtonDruga = findViewById(R.id.odjava_drugaGaraza);

        odjavaButtonDruga.setOnClickListener(v -> povratakNaGaraze());

        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    public void povratakNaGaraze(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
    }
}
