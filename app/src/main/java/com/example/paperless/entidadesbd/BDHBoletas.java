package com.example.paperless.entidadesbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHBoletas extends SQLiteOpenHelper {
    public static final String NOMBRE_BD_BOLETAS = "bd_boletas";
    public static final String NOMBRE_TABLA_BOLETAS = "tabla_boletas";
    public static final String FOLIO = "folio";
    public static final String NOMBRE_COMERCIO = "nombre_comercio";
    public static final String RUT_COMERCIO = "rut_comercio";
    public static final String DESCRIPCION_COMERCIO = "descripcion_comercio";
    public static final String DIRECCION_COMERCIO = "direccion_comercio";
    public static final String CONTACTO_COMERCIO = "contacto_comercio";
    public static final String FECHA = "fecha";
    public static final String DETALLE = "detalle";
    public static final String DESCUENTO = "descuento";
    public static final String TOTAL = "total";
    public static final String[] CAMPOS = {FOLIO, NOMBRE_COMERCIO, RUT_COMERCIO,
            DESCRIPCION_COMERCIO, DIRECCION_COMERCIO, CONTACTO_COMERCIO,
            FECHA, DETALLE, DESCUENTO, TOTAL};
    public static final String CREAR_TABLA_BOLETAS = "CREATE TABLE " + NOMBRE_TABLA_BOLETAS + " (" +
            FOLIO + " TEXT PRIMARY KEY, " +
            NOMBRE_COMERCIO + " TEXT, " + RUT_COMERCIO + " TEXT, " +
            DESCRIPCION_COMERCIO + " TEXT, " + DIRECCION_COMERCIO + " TEXT, " +
            CONTACTO_COMERCIO + " TEXT, " + FECHA + " TEXT, " +
            DETALLE + " TEXT, " + DESCUENTO + " TEXT, " +
            TOTAL + " TEXT)";
    public static final String DROP_TABLA_BOLETAS = "DROP TABLE IF EXISTS " + NOMBRE_TABLA_BOLETAS;

    public BDHBoletas(@Nullable Context context, @Nullable String name,
                      @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREAR_TABLA_BOLETAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL(DROP_TABLA_BOLETAS);
        onCreate(bd);
    }
}
