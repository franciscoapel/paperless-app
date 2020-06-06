package com.example.paperless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle("Boletas");
        }

        rv_boletas = findViewById(R.id.rv_boletas);
        rv_boletas.setLayoutManager(new LinearLayoutManager(this));

        listaBoletas = new ArrayList<>();
        for (int i=0; i<10; i++) {
            String[] boleta = new String[2];
            boleta[0] = String.valueOf(i);
            boleta[1] = String.valueOf(i) + i;
            listaBoletas.add(boleta);
        }

        rv_boletas.setAdapter(new RVAdapterBoletas(listaBoletas));


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.boletas_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}