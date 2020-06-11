package com.example.paperless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paperless.entidadesbd.BDHPaperless;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    EditText et_nombres, et_apellidos, et_rut, et_email, et_contrasena;
    TextView tv_inicia_aqui, tv_hintContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_nombres = findViewById(R.id.et_nombres_registro_activity);
        et_apellidos = findViewById(R.id.et_apellidos_registro_activity);
        et_rut = findViewById(R.id.et_rut_registro_activity);
        et_email = findViewById(R.id.et_email_registro_activity);
        et_contrasena = findViewById(R.id.et_contrasena_registro_activity);
        tv_inicia_aqui = findViewById(R.id.tv_inicia_aqui_registro_activity);
        tv_hintContrasena = findViewById(R.id.tv_hint_contrasena);
    }

    public void registrar(View view) {
        if (et_nombres.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un nombre.", Toast.LENGTH_SHORT).show();
        } else if (et_apellidos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese apellidos.", Toast.LENGTH_SHORT).show();
        } else if (et_rut.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese rut.", Toast.LENGTH_SHORT).show();
        } else if (!rutEsValido(et_rut.getText().toString())) {
            Toast.makeText(this, "Ingrese rut de la forma XXXXXXXX-X.",
                    Toast.LENGTH_LONG).show();
        } else if (et_email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese email.", Toast.LENGTH_SHORT).show();
        } else if (!emailEsValido(et_email.getText().toString())) {
            Toast.makeText(this, "Ingrese email valido.", Toast.LENGTH_SHORT).show();
        } else if (et_contrasena.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese contraseña.", Toast.LENGTH_SHORT).show();
        } else if (!contrasenaEsValida(et_contrasena.getText().toString())) {
            Toast.makeText(this, "Contraseña invalida.", Toast.LENGTH_SHORT).show();
        } else {
            BDHPaperless bdh = new BDHPaperless(this,
                    BDHPaperless.NOMBRE_BD_PAPERLESS, null, 1);
            SQLiteDatabase bdPaperless = bdh.getWritableDatabase();
            ContentValues cValues = new ContentValues();
            cValues.put(BDHPaperless.NOMBRES_USUARIO, et_nombres.getText().toString());
            cValues.put(BDHPaperless.APELLIDOS_USUARIO, et_apellidos.getText().toString());
            cValues.put(BDHPaperless.RUT_USUARIO, et_rut.getText().toString());
            cValues.put(BDHPaperless.EMAIL_USUARIO, et_email.getText().toString());
            cValues.put(BDHPaperless.CONTRASENA_USUARIO, et_contrasena.getText().toString());
            if (bdPaperless.insert
                    (BDHPaperless.NOMBRE_TABLA_USUARIOS, null, cValues) == -1) {
                Toast.makeText(this,
                        "Rut ya ingresado, imposible llevar a cabo el registro.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, IngresoActivity.class);
                startActivity(i);
            }
        }
    }

    public void inicia_aqui(View view){
        startActivity(new Intent(this, IngresoActivity.class));}

    public void mostrarHintContrasena(View view) {
        if (tv_hintContrasena.getVisibility()==View.GONE) {
            tv_hintContrasena.setVisibility(View.VISIBLE);
        } else {
            tv_hintContrasena.setVisibility(View.GONE);
        }
    }

    private boolean emailEsValido(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    //Rut de la forma XXXXXXXX-X
    private Boolean rutEsValido (String rut) {
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if (!matcher.matches()) return false;
        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
    }
    private String dv ( String rut ) {
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
    }

    private Boolean contrasenaEsValida(String contrasena) {
        String patron = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (contrasena.matches(patron)) return true;
        return false;
    }
}
