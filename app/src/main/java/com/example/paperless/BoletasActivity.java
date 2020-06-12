package com.example.paperless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.paperless.entidadesbd.BDHPaperless;
import com.example.paperless.entidadesbd.Boleta;

import java.util.ArrayList;

public class BoletasActivity extends AppCompatActivity {

    ArrayList<Boleta> listaBoletas;
    RecyclerView rv_boletas;
    RVAdapterBoletas rvaBoletas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletas);

        Toolbar toolbar = findViewById(R.id.boletas_activity_toolbar);
        setSupportActionBar(toolbar);

        BDHPaperless bdh = new BDHPaperless
                (this, BDHPaperless.NOMBRE_BD_PAPERLESS, null, 1);
        SQLiteDatabase bdBoletas = bdh.getReadableDatabase();
        Cursor cursor = bdBoletas.query(BDHPaperless.NOMBRE_TABLA_BOLETAS, BDHPaperless.CAMPOS_BOLETA,
                BDHPaperless.RUT_CLIENTE_BOLETA + "=?",
                new String[]{IngresoActivity.usuario.getRut()},null, null,
                BDHPaperless.FECHA_BOLETA);
        listaBoletas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Boleta boleta = new Boleta(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6),
                    cursor.getString(7), cursor.getString(8),
                    cursor.getString(9), cursor.getString(10));
            listaBoletas.add(boleta);
        }
        bdBoletas.close();
        cursor.close();
        rv_boletas = findViewById(R.id.rv_boletas);
        rv_boletas.setLayoutManager(new LinearLayoutManager(this));
        rvaBoletas = new RVAdapterBoletas(listaBoletas);
        rv_boletas.setAdapter(rvaBoletas);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_boletas_activity, menu);
        MenuItem itemBuscar = menu.findItem(R.id.buscar_menu);
        SearchView barraBusqueda = (SearchView)itemBuscar.getActionView();
        barraBusqueda.setImeOptions(EditorInfo.IME_ACTION_DONE);
        barraBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                rvaBoletas.getFilter().filter(newText);
                return false;
            }
        });
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage("Quieres cerrar sesion?");
        adb.setNegativeButton("No", null);
        adb.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                IngresoActivity.usuario = null;
                finish();
                BoletasActivity.super.onBackPressed();
            }
        });
        adb.create().show();
    }
}