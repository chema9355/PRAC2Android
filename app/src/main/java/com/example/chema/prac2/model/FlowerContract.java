package com.example.chema.prac2.model;

import android.provider.BaseColumns;

public abstract class FlowerContract {


    public FlowerContract() {
    }

    public static class tablaFlowers implements BaseColumns {

        public static final String TABLE_NAME = "flowers";

        public static final String COL_NAME_ID = _ID;
        public static final String COL_NAME_NOMBRE = "nombre";
        public static final String COL_NAME_CATEGORIA = "categoria";
        public static final String COL_NAME_INSTRUCCIONES = "instrucciones";
        public static final String COL_NAME_PRECIO = "precio";
        public static final String COL_NAME_URL = "url_imagen";


    }
}