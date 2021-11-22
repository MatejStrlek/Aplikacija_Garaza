package com.example.aplikacija_garaza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //deklaracija gumba
    Button prvaGarazaButton, drugaGarazaButton, trecaGarazaButton, googleMapsButton, odjavaButton;

    //glavna metoda u kojoj se piše glavni dio koda
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //povezivanje Java dokumenta sa XML dokumentom
        setContentView(R.layout.activity_main);

        //povezivanje gore navedenih gumba sa inputima u XML file-u
        prvaGarazaButton = findViewById(R.id.prvaGaraza);
        drugaGarazaButton = findViewById(R.id.drugaGaraza);
        googleMapsButton = findViewById(R.id.googleMapsButton_Main);
        trecaGarazaButton = findViewById(R.id.trecaGaraza);
        odjavaButton = findViewById(R.id.odjava_Main);

        /* metoda OnClickListener, kada se pritisne gumb za prvu garazu,
           poziva se metoda koja otvara zaslon prve garaže */
        prvaGarazaButton.setOnClickListener(v -> otvoriPrvuGarazu());

        /* metoda OnClickListener, kada se pritisne gumb za drugu garazu,
           poziva se metoda koja otvara zaslon druge garaže */
        drugaGarazaButton.setOnClickListener(v -> otvoriDruguGarazu());

        /* metoda OnClickListener, kada se pritisne gumb za treću garazu,
           poziva se metoda koja otvara zaslon treće garaže */
        trecaGarazaButton.setOnClickListener(v -> otvoriTrecuGarazu());

        /* metoda OnClickListener, kada se pritisne gumb za lokacije garaža,
           poziva se metoda koja otvara zaslon Google Maps API-ja */
        googleMapsButton.setOnClickListener(v -> otvoriGoogleMaps());

        /* metoda OnClickListener, kada se pritisne gumb za odjavu,
           poziva se metoda koja odjavljuje korisnika */
        odjavaButton.setOnClickListener(v -> {
            povratakNaIndex();
            Toast.makeText(MainActivity.this, "Odjavljeni ste!", Toast.LENGTH_SHORT).show();
        });

        //Action bar se miče jer je nepotreban
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    //metoda za prikaz detalja o prvoj garaži
    public void otvoriPrvuGarazu(){
        Intent intent = new Intent(this, GarazaSpansko.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
    }

    //metoda za prikaz detalja o drugoj garaži
    public void otvoriDruguGarazu(){
        Intent intent = new Intent(this, GarazaJarun.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);
    }

    //metoda za prikaz detalja o trećoj garaži
    public void otvoriTrecuGarazu(){
        Intent intent = new Intent(this, GarazaVoltino.class);
        startActivity(intent);
    }

    //metoda za prikaz Google Maps lokacija garaža
    public void otvoriGoogleMaps(){
        Intent intent = new Intent(this, GoogleMaps.class);
        startActivity(intent);
    }

    //metoda za povratak na početni zaslon aplikacije
    public void povratakNaIndex(){
        Intent intent = new Intent(this, Index.class);
        startActivity(intent);
    }
}
