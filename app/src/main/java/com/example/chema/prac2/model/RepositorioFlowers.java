package com.example.chema.prac2.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.chema.prac2.model.FlowerContract.tablaFlowers;

public class RepositorioFlowers extends SQLiteOpenHelper {

    private static final String DATABASE_FILENAME = "Flowers.db";

    private static final int DATABASE_VERSION = 1;

    public RepositorioFlowers(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
     //   this.deleteAll();
        String sentenciaSQL = "CREATE TABLE " + tablaFlowers.TABLE_NAME + "("
                + tablaFlowers.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaFlowers.COL_NAME_NOMBRE + " TEXT, "
                + tablaFlowers.COL_NAME_CATEGORIA + " TEXT, "
                + tablaFlowers.COL_NAME_INSTRUCCIONES + " TEXT, "
                + tablaFlowers.COL_NAME_PRECIO + " DOUBLE, "
                + tablaFlowers.COL_NAME_URL + " TEXT)";
        db.execSQL(sentenciaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sentenciaSQL = "DROP TABLE IF EXISTS " + tablaFlowers.TABLE_NAME;
        db.execSQL(sentenciaSQL);
        onCreate(db);
    }

    public long add(Flower flower) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(tablaFlowers.COL_NAME_NOMBRE, flower.getName());
        valores.put(tablaFlowers.COL_NAME_CATEGORIA, flower.getCategory());
        valores.put(tablaFlowers.COL_NAME_INSTRUCCIONES, flower.getInstructions());
        valores.put(tablaFlowers.COL_NAME_PRECIO, flower.getPrice());
        valores.put(tablaFlowers.COL_NAME_URL, flower.getPhoto());

        return db.insert(tablaFlowers.TABLE_NAME, null, valores);
    }


    public ArrayList<Flower> getAll() {
        return getAll(tablaFlowers.COL_NAME_ID, true);
    }

    public ArrayList<Flower> getAll(String columna, boolean ordenAscendente) {
        ArrayList<Flower> resultado = new ArrayList<>();
        String consultaSQL = "SELECT * FROM " + tablaFlowers.TABLE_NAME
                + " ORDER BY " + columna + (ordenAscendente ? " ASC" : " DESC");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Flower flower = new Flower(
                        cursor.getInt(cursor.getColumnIndex(tablaFlowers.COL_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(tablaFlowers.COL_NAME_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(tablaFlowers.COL_NAME_CATEGORIA)),
                        cursor.getString(cursor.getColumnIndex(tablaFlowers.COL_NAME_INSTRUCCIONES)),
                        cursor.getDouble(cursor.getColumnIndex(tablaFlowers.COL_NAME_PRECIO)),
                        cursor.getString(cursor.getColumnIndex(tablaFlowers.COL_NAME_URL))
                );
                resultado.add(flower);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return resultado;
    }


    public long deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tablaFlowers.TABLE_NAME, "1", null);
    }

    public Flower getFutbolistaByID(int id) {
        String consultaSQL = "SELECT * FROM " + tablaFlowers.TABLE_NAME
                + " WHERE " + tablaFlowers.COL_NAME_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Flower flower = null;
        Cursor cursor = db.rawQuery(
                consultaSQL,
                new String[]{ String.valueOf(id) }
        );

        if (cursor.moveToFirst()) {
            flower = new Flower(
                    cursor.getInt(cursor.getColumnIndex(tablaFlowers.COL_NAME_ID)),
                    cursor.getString(cursor.getColumnIndex(tablaFlowers.COL_NAME_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(tablaFlowers.COL_NAME_CATEGORIA)),
                    cursor.getString(cursor.getColumnIndex(tablaFlowers.COL_NAME_INSTRUCCIONES)),
                    cursor.getDouble(cursor.getColumnIndex(tablaFlowers.COL_NAME_PRECIO)),
                    cursor.getString(cursor.getColumnIndex(tablaFlowers.COL_NAME_URL))
            );
            cursor.close();
        }

        return flower;
    }

}
