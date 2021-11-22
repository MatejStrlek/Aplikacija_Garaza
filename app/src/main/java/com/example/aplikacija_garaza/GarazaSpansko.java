package com.example.aplikacija_garaza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GarazaSpansko extends AppCompatActivity {
    //deklaracija gumba
    Button odjavaButtonPrva;

    //glavna metoda u kojoj se piše glavni dio koda
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //povezivanje Java dokumenta sa XML dokumentom
        setContentView(R.layout.prvagaraza);

        //povezivanje gumba sa gumbom u XML file-u
        odjavaButtonPrva = findViewById(R.id.odjava_prvaGaraza);

        /* metoda OnClickListener, kada se pritisne gumb za povratak natrag,
           poziva se metoda koja vraća na glavni zaslon */
        odjavaButtonPrva.setOnClickListener(v -> povratakNaGaraze());

        //Action bar se miče jer je ne potreban
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    //metoda za povratak na glavni zaslon aplikacije
    public void povratakNaGaraze() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);
    }
}
