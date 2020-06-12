package com.example.paperless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paperless.entidadesbd.BDHPaperless;
import com.example.paperless.entidadesbd.Usuario;

public class IngresoActivity extends AppCompatActivity {

    public static Usuario usuario;
    EditText et_rut, et_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        et_rut = findViewById(R.id.et_rut_ingreso_activity);
        et_contrasena = findViewById(R.id.et_contrasena_ingreso_activity);


    }

    public void ingresar(View view) {
        if (et_rut.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese rut", Toast.LENGTH_SHORT).show();
        } else if (et_contrasena.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese contraseña", Toast.LENGTH_SHORT).show();
        } else {
            BDHPaperless bdh = new BDHPaperless(this,
                    BDHPaperless.NOMBRE_BD_PAPERLESS, null, 1);
            SQLiteDatabase bdPaperless = bdh.getReadableDatabase();
            Cursor cursor = bdPaperless.query(BDHPaperless.NOMBRE_TABLA_USUARIOS,
                    BDHPaperless.CAMPOS_USUARIO, BDHPaperless.RUT_USUARIO + " =?",
                    new String[]{et_rut.getText().toString()},null, null,
                    null);
            if (cursor.moveToFirst()) {//*******************************
                if (!et_contrasena.getText().toString().equals(cursor.getString(4))) {
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                usuario = new Usuario(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4));
                bdPaperless.close();
                cursor.close();
                startActivity(new Intent(this, BoletasActivity.class));
            } else {
                bdPaperless.close();
                cursor.close();
                Toast.makeText(this, "Rut no registrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}