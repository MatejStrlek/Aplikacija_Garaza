package com.example.aplikacija_garaza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Index extends AppCompatActivity {

    //deklaracija dva gumba
    Button buttonLoginIndex, buttonRegistrationIndex;

    //glavna metoda u kojoj se piše glavni dio koda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //povezivanje Java dokumenta sa XML dokumentom
        setContentView(R.layout.activity_index);

        //povezivanje gore navedenih varijabli sa inputima u XML file-u
        buttonLoginIndex = findViewById(R.id.buttonLoginIndex);
        buttonRegistrationIndex = findViewById(R.id.buttonRegistrationIndex);

        /* metoda OnClickListener, kada se pritisne gumb za prijavu,
        zaslon se mijenja iz početnog u zaslon za prijavu korisnika */
        buttonLoginIndex.setOnClickListener(v -> openLogin());

        /* metoda OnClickListener, kada se pritisne gumb za registraciju,
        zaslon se mijenja iz početnog u zaslon za registraciju korisnika */
        buttonRegistrationIndex.setOnClickListener(v -> openRegister());

        //Action bar se miče jer je nepotreban
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    //metoda za otvaranje zaslona za prijavu korisnika
    private void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        //animacija koja se događa zbog promjene iz jednog zaslona u drugi
        overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
    }

    //metoda za otvaranje zaslona za registraciju korisnika
    private void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        //animacija koja se događa zbog promjene iz jednog zaslona u drugi
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);
    }

}