package com.example.aplikacija_garaza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GarazaVoltino extends AppCompatActivity {
    Button odjavaButtonTreca;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trecagaraza);

        odjavaButtonTreca = findViewById(R.id.odjava_trecaGaraza);

        odjavaButtonTreca.setOnClickListener(v -> povratakNaGaraze());

        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    public void povratakNaGaraze(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
