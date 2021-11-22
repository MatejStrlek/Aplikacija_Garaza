package com.example.aplikacija_garaza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //deklariranje imena lokalne baze podataka
    public static final String DBNAME = "Login.db";

    //konstruktor baze podataka
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    //metoda za izradu baze podataka
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, password TEXT, name TEXT)");
    }

    //metoda koja provjerava da li postoji već ta baza
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");
    }

    //metoda koja služi za ubacivanje upisanih podataka iz polja za registraciju u bazu
    //konkretno se samo pohranjuju email i password zbog toga što nam to treba prilikom prijave
    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = MyDB.insert("users", null, contentValues);

        return result != -1;
    }

    //metoda koja provjerava da li upisani mail već postoji u bazi
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ?", new String[] {email});

        return cursor.getCount() > 0;
    }

    //metoda koja provjerava da li upisani password već postoji u bazi
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[] {email, password});

        return cursor.getCount() > 0;
    }
}
