package com.example.aplikacija_garaza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Register extends AppCompatActivity {
    //deklaracija inputa, gumba i baze podataka (DBHelper)
    EditText inputNameRegister, inputEmailRegister, inputPasswordRegister, inputPasswordConfirmRegister;
    Button buttonRegister, buttonGoBack;
    DBHelper DB;

    //glavna metoda u kojoj se piše glavni dio koda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //povezivanje Java dokumenta sa XML dokumentom
        setContentView(R.layout.activity_register);

        //povezivanje gore navedenih varijabli sa inputima u XML file-u
        inputNameRegister = findViewById(R.id.inputNameRegister);
        inputEmailRegister = findViewById(R.id.inputEmailRegister);
        inputPasswordRegister = findViewById(R.id.inputPasswordRegister);
        inputPasswordConfirmRegister = findViewById(R.id.inputPasswordConfirmRegister);

        buttonRegister = findViewById(R.id.buttonRegister);
        buttonGoBack = findViewById(R.id.buttonGoBackRegister);

        DB = new DBHelper(this);

        /* metoda OnClickListener, kada se pritisne gumb,
        podaci se pohranjuju u nove varijable i šalju se u bazu podataka */
        buttonRegister.setOnClickListener(v -> {
            String name = inputNameRegister.getText().toString();
            String email = inputEmailRegister.getText().toString();
            String password = inputPasswordRegister.getText().toString();
            String confirmPassword = inputPasswordConfirmRegister.getText().toString();

            //metoda za slanje podataka u bazu
            checkData(name, email, password, confirmPassword);
        });

        /* metoda OnClickListener, kada se pritisne gumb,
          poziva novu metodu koja vraća korisnika na početnu stranicu*/
        buttonGoBack.setOnClickListener(v -> goBack());

        //Action bar se miče jer je ne potreban
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    //metoda za povratak na početnu stranicu
    private void goBack() {
        Intent intent = new Intent(this, Index.class);
        startActivity(intent);
        //animacija za prijelaz sa jednog layouta na drugi
        overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
    }

    //metoda za provjeru nekih uvjeta za upis podataka
    private void checkData(String name, String email, String password, String confirmPassword) {
        if(TextUtils.isEmpty(name)){
            inputNameRegister.setError("Morate unijeti svoje ime");
            Toast.makeText(this, "Morate unijeti svoje ime", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(email)){
            inputEmailRegister.setError("Morate unijeti email");
            Toast.makeText(this, "Morate unijeti email", Toast.LENGTH_SHORT).show();
        }

        else if(!email.contains("@") || !email.contains(".")){
            inputEmailRegister.setError("Morate unijeti valjani email");
            Toast.makeText(this, "Morate unijeti valjani email", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            inputPasswordRegister.setError("Morate unijeti šifru");
            Toast.makeText(this, "Morate unijeti šifru", Toast.LENGTH_SHORT).show();
        }

        else if(password.length() < 6){
            inputPasswordRegister.setError("Šifra mora imati najmanje 6 znakova");
            Toast.makeText(this, "Šifra mora imati najmanje 6 znakova", Toast.LENGTH_SHORT).show();
        }

        else if(!confirmPassword.equals(password)){
            inputPasswordConfirmRegister.setError("Šifre se ne podudaraju");
            Toast.makeText(this, "Šifre se ne podudaraju", Toast.LENGTH_SHORT).show();
        }

        /* ako su svi uvjeti zadovoljeni, podaci se šalju u bazu
           i dobivamo obavijest da je pohrana podataka uspješna */
        else{
            Boolean checkEmail = DB.checkEmail(email);

            if(!checkEmail) {
                Boolean insertData = DB.insertData(email, password);

                if(insertData){
                    Toast.makeText(Register.this, "Registracija uspješna!", Toast.LENGTH_SHORT).show();
                    goToMain();
                }
                else{
                    Toast.makeText(Register.this, "Registracija nije uspjela!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(Register.this, "Korisnik već postoji!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //metoda za ulazak u glavni dio aplikacije
    private void goToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}