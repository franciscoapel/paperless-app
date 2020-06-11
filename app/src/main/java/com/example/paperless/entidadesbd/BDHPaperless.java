package com.example.paperless.entidadesbd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHPaperless extends SQLiteOpenHelper {
    public static final String NOMBRE_BD_PAPERLESS = "bd_paperless";
    public static final String NOMBRE_TABLA_BOLETAS = "tabla_boletas";
    public static final String FOLIO_BOLETA = "folio";
    public static final String NOMBRE_COMERCIO_BOLETA = "nombre_comercio";
    public static final String RUT_COMERCIO_BOLETA = "rut_comercio";
    public static final String DESCRIPCION_COMERCIO_BOLETA = "descripcion_comercio";
    public static final String DIRECCION_COMERCIO_BOLETA = "direccion_comercio";
    public static final String CONTACTO_COMERCIO_BOLETA = "contacto_comercio";
    public static final String FECHA_BOLETA = "fecha";
    public static final String DETALLE_BOLETA = "detalle";
    public static final String DESCUENTO_BOLETA = "descuento";
    public static final String TOTAL_BOLETA = "total";
    public static final String RUT_CLIENTE_BOLETA = "rut_cliente";
    public static final String[] CAMPOS_BOLETA = {FOLIO_BOLETA, NOMBRE_COMERCIO_BOLETA, RUT_COMERCIO_BOLETA,
            DESCRIPCION_COMERCIO_BOLETA, DIRECCION_COMERCIO_BOLETA, CONTACTO_COMERCIO_BOLETA,
            FECHA_BOLETA, DETALLE_BOLETA, DESCUENTO_BOLETA, TOTAL_BOLETA, RUT_CLIENTE_BOLETA};
    public static final String CREAR_TABLA_BOLETAS = "CREATE TABLE " + NOMBRE_TABLA_BOLETAS + " (" +
            FOLIO_BOLETA + " TEXT PRIMARY KEY, " +
            NOMBRE_COMERCIO_BOLETA + " TEXT, " + RUT_COMERCIO_BOLETA + " TEXT, " +
            DESCRIPCION_COMERCIO_BOLETA + " TEXT, " + DIRECCION_COMERCIO_BOLETA + " TEXT, " +
            CONTACTO_COMERCIO_BOLETA + " TEXT, " + FECHA_BOLETA + " TEXT, " +
            DETALLE_BOLETA + " TEXT, " + DESCUENTO_BOLETA + " TEXT, " +
            TOTAL_BOLETA + " TEXT, " + RUT_CLIENTE_BOLETA + " TEXT)";
    public static final String DROP_TABLA_BOLETAS = "DROP TABLE IF EXISTS " + NOMBRE_TABLA_BOLETAS;

    public static final String NOMBRE_TABLA_USUARIOS = "tabla_usuarios";
    public static final String NOMBRES_USUARIO= "nombres";
    public static final String APELLIDOS_USUARIO = "apellidos";
    public static final String RUT_USUARIO = "rut";
    public static final String EMAIL_USUARIO = "email";
    public static final String CONTRASENA_USUARIO = "contrasena";
    public static final String[] CAMPOS_USUARIO = {NOMBRES_USUARIO, APELLIDOS_USUARIO, RUT_USUARIO,
            EMAIL_USUARIO, CONTRASENA_USUARIO};
    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE " + NOMBRE_TABLA_USUARIOS +" (" +
            NOMBRES_USUARIO + " TEXT, " + APELLIDOS_USUARIO + " TEXT, " +
            RUT_USUARIO + " TEXT PRIMARY KEY, " +
            EMAIL_USUARIO + " TEXT, " + CONTRASENA_USUARIO + " TEXT)";
    public static final String DROP_TABLA_USUARIOS = "DROP TABLE IF EXISTS " + NOMBRE_TABLA_USUARIOS;

    public BDHPaperless(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREAR_TABLA_BOLETAS);
        bd.execSQL(CREAR_TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL(DROP_TABLA_BOLETAS);
        bd.execSQL(DROP_TABLA_USUARIOS);
        onCreate(bd);
    }
}
