package com.example.aplikacija_garaza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Login extends AppCompatActivity {

    //deklaracija inputa, gumba i baze podataka (DBHelper)
    EditText inputEmailLogin, inputPasswordLogin;
    Button buttonLogin, buttonGoBack;
    DBHelper DB;

    //glavna metoda u kojoj se piše glavni dio koda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //povezivanje Java dokumenta sa XML dokumentom
        setContentView(R.layout.activity_login);

        //povezivanje gore navedenih varijabli sa inputima u XML file-u
        inputEmailLogin = findViewById(R.id.inputEmailLogin);
        inputPasswordLogin = findViewById(R.id.inputPasswordLogin);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonGoBack = findViewById(R.id.buttonGoBackLogin);

        //baza
        DB = new DBHelper(this);

        /* metoda OnClickListener, kada se pritisne gumb,
        podaci se uspoređuju sa onima u bazi (provjerava se da li postoji
         taj user i da li je upisao točan password */
        buttonLogin.setOnClickListener(v -> {
            String email = inputEmailLogin.getText().toString();
            String password = inputPasswordLogin.getText().toString();

            //uvjeti koji se moraju provjerit
            if(TextUtils.isEmpty(email)){
                inputEmailLogin.setError("Morate unijeti svoj email!");
            }

            else if(!email.contains("@") || !email.contains(".")){
                inputEmailLogin.setError("Morate unijeti valjani email");
            }

            else if(TextUtils.isEmpty(password)){
                inputPasswordLogin.setError("Morate unijeti šifru!");
            }

            else {
                /*email i password se prosljeđuju u metodu koja provjerava da li
                 je korisnik u lokalnoj bazi */
                Boolean checkEmailPassword = DB.checkEmailPassword(email, password);

                //ako je, prijava je uspješna iotvara se glavni zaslon aplikacije
                if(checkEmailPassword){
                    Toast.makeText(Login.this, "Prijava uspješna!", Toast.LENGTH_SHORT).show();
                    goToMain();
                }
                //ako nije, baca se obavijest
                else{
                    Toast.makeText(Login.this, "Prijava nije uspjela! Krivi podaci za prijavu!", Toast.LENGTH_SHORT).show();
                    inputPasswordLogin.setText("");
                }
            }
        });

        /* metoda OnClickListener, kada se pritisne gumb,
           vraćanje natrag u početni zaslon */
        buttonGoBack.setOnClickListener(v -> goBack());

        //Action bar se miče jer je nepotreban
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    //metoda za ulazak u glavni dio aplikacije
    private void goToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    //metoda za vraćanje na početni zaslon
    private void goBack() {
        Intent intent = new Intent(this, Index.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);
    }
}