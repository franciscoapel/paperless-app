package com.example.paperless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {

    TextView tv_nombres, tv_apellidos, tv_rut, tv_email, tv_inicia_aqui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        tv_nombres = findViewById(R.id.tv_nombres_registro_activity);
        tv_apellidos = findViewById(R.id.tv_apellidos_registro_activity);
        tv_rut = findViewById(R.id.tv_rut_registro_activity);
        tv_email = findViewById(R.id.tv_email_registro_activity);
        tv_inicia_aqui = findViewById(R.id.tv_inicia_aqui_registro_activity);
    }

    public void irABoletas(View view) {


        Intent i = new Intent(this, BoletasActivity.class);
        startActivity(i);
    }
}
