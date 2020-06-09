package com.example.paperless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.paperless.entidadesbd.BDHBoletas;

import java.util.ArrayList;

public class BoletasActivity extends AppCompatActivity {

    ArrayList<String[]> listaBoletas;
    RecyclerView rv_boletas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletas);

        Toolbar toolbar = findViewById(R.id.boletas_activity_toolbar);
        setSupportActionBar(toolbar);

        listaBoletas = new ArrayList<>();
        for (int i=0; i<10; i++) {
            String[] boleta = new String[2];
            boleta[0] = String.valueOf(i);
            boleta[1] = String.valueOf(i) + i;
            listaBoletas.add(boleta);
        }
        rv_boletas = findViewById(R.id.rv_boletas);
        rv_boletas.setLayoutManager(new LinearLayoutManager(this));
        rv_boletas.setAdapter(new RVAdapterBoletas(listaBoletas));

        BDHBoletas bd_helper = new BDHBoletas
                (this, BDHBoletas.NOMBRE_BD_BOLETAS, null, 1);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_boletas_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.agregar_menu:
                startActivity(new Intent(this, EscanerBoletaActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}