package com.example.aplikacija_garaza

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMaps : AppCompatActivity(), OnMapReadyCallback {

    //deklariranje Google Maps objekta
    private lateinit var map: GoogleMap

    /*korisnik mora odabrati da li želi da ova alplikacija koristi
      njegovu trenutnu aplikaciju */
    private val LOCATION_PERMISSION_REQUEST = 1

    //metoda za otkrivanje lokacije korisnika
    private fun getLocationAccess() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        }
        else
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
    }

    //prilikom otvaranja ovog zaslona se pita korisnika da li želi da mu aplikacija koristi lokaciju
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return
                }
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return
                }
                map.isMyLocationEnabled = true
            }
            else {
                Toast.makeText(this, "Niste dopustili pristup lokaciji", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }


    //glavna metoda u kojoj se piše glavni dio koda
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //povezivanje Java dokumenta sa XML dokumentom
        setContentView(R.layout.activity_google_maps)

        //deklaracija mapFragmenta koji će se prikazati u XML prikazu zaslona
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Action bar se miče jer je ne potreban
        supportActionBar?.hide()
    }

    //metoda u koju se postavljaju markeri svake garaže
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        //deklariranje tipa mape da je hibridna radi lakše preglednosti
        map.mapType = GoogleMap.MAP_TYPE_HYBRID

        //geografska lokacija garaža
        val spansko = LatLng(45.803026, 15.888310)
        val jarun = LatLng(45.786782, 15.917082)
        val voltino = LatLng(45.80032, 15.92577)

        map.addMarker(MarkerOptions().position(spansko).title("Garaža u Španskom"))
        map.addMarker(MarkerOptions().position(jarun).title("Garaža na Jarunu"))
        map.addMarker(MarkerOptions().position(voltino).title("Garaža u Voltinu"))

        //mogućnost postavljanja i pomicanja Google Maps karte
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(spansko, 12f))

        //pozivanje metode za lociranje korisnika
        getLocationAccess()
    }
}