package com.example.paperless.entidadesbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHBoletas extends SQLiteOpenHelper {
    public static final String NOMBRE_BD_BOLETAS = "bd_boletas";
    public static final String NOMBRE_TABLA_BOLETAS = "boletas";
    public static final String CREAR_TABLA_BOLETAS = "CREATE TABLE " + NOMBRE_TABLA_BOLETAS +
            " (folio INTEGER PRIMARY KEY," +
            " nombre_comercio TEXT, rut_comercio TEXT, descripcion_comercio TEXT," +
            " direccion_comercio TEXT, contacto_comercio TEXT, fecha TEXT, detalle Text," +
            " descuento INTEGER, total INTEGER)";
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
