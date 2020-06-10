package com.example.paperless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.paperless.entidadesbd.BDHBoletas;
import com.example.paperless.entidadesbd.Boleta;

import java.util.ArrayList;

public class BoletasActivity extends AppCompatActivity {

    ArrayList<Boleta> listaBoletas;
    RecyclerView rv_boletas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletas);

        Toolbar toolbar = findViewById(R.id.boletas_activity_toolbar);
        setSupportActionBar(toolbar);

        BDHBoletas bd_helper = new BDHBoletas
                (this, BDHBoletas.NOMBRE_BD_BOLETAS, null, 1);
        SQLiteDatabase bdBoletas = bd_helper.getReadableDatabase();
        Cursor cursor = bdBoletas.query(BDHBoletas.NOMBRE_TABLA_BOLETAS, BDHBoletas.CAMPOS,
                null, null, null, null, BDHBoletas.FECHA);
        listaBoletas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Boleta boleta = new Boleta(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7),
                    cursor.getString(8), cursor.getString(9));
            listaBoletas.add(boleta);
        }
        bdBoletas.close();
        cursor.close();
        rv_boletas = findViewById(R.id.rv_boletas);
        rv_boletas.setLayoutManager(new LinearLayoutManager(this));
        rv_boletas.setAdapter(new RVAdapterBoletas(listaBoletas));

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