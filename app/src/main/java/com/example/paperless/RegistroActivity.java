package com.example.paperless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


    }

    public void irABoletas(View view) {
        Intent i = new Intent(this, BoletasActivity.class);
        startActivity(i);
    }
}
