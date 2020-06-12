package com.example.paperless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paperless.entidadesbd.BDHPaperless;
import com.example.paperless.entidadesbd.Boleta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoletasActivity extends AppCompatActivity {

    ArrayList<Boleta> listaBoletas;
    ArrayList<Boleta> listaBoletasCompleta;
    RecyclerView rv_boletas;
    RVAdapterBoletas rvaBoletas;
    FloatingActionButton fabCerrarCesion;

    String[] fechasFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletas);

        fabCerrarCesion = findViewById(R.id.fab_boletas_activity);
        fabCerrarCesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoCerrarSesion();
            }
        });

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

        listaBoletasCompleta = new ArrayList<>(listaBoletas);
        fechasFiltro = new String[2];

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_boletas_activity, menu);
        MenuItem itemBuscar = menu.findItem(R.id.item_buscar_boletas_activity);
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
            case R.id.item_agregar_boletas_activity:
                startActivity(new Intent(this, EscanerBoletaActivity.class));
                return true;
            case R.id.item_calendario_boletas_activity:
                mostrarCalendario(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarCalendario(final int contadorRecursividad) {
        final Dialog dialogoCalendario = new Dialog(this);
        dialogoCalendario.setContentView(R.layout.calendario_dialogo);
        TextView tv_desdehasta = dialogoCalendario.findViewById(R.id.tv_desdehasta_calendario);
        if (contadorRecursividad==0) {
            dialogoCalendario.getWindow().getAttributes()
                    .windowAnimations = R.style.DialogAnimationDesde;
            tv_desdehasta.setText("DESDE");
            rvaBoletas.listaBoletas.clear();
            rvaBoletas.listaBoletas.addAll(listaBoletasCompleta);
            rvaBoletas.notifyDataSetChanged();
        } else {
            dialogoCalendario.getWindow().getAttributes()
                    .windowAnimations = R.style.DialogAnimationHasta;
            tv_desdehasta.setText("HASTA");
        }
        CalendarView calendario = dialogoCalendario.findViewById(R.id.calendario_boletas_activity);
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange
                    (@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "-" + (month+1) + "-" + year;
                if (contadorRecursividad == 0) {
                    fechasFiltro[0] = fecha;
                    mostrarCalendario(1);
                    dialogoCalendario.dismiss();
                } else {
                    fechasFiltro[1] = fecha;
                    dialogoCalendario.dismiss();
                    try {
                        filtrarPorFecha(fechasFiltro);}
                    catch (ParseException e) {e.printStackTrace();}
                    Toast.makeText(BoletasActivity.this, fechasFiltro[0] + " / " + fechasFiltro[1], Toast.LENGTH_SHORT).show();

                }
            }
        });
        dialogoCalendario.show();
    }

    public void filtrarPorFecha(String[] fechasFiltro) throws ParseException {
        ArrayList<Boleta> listaFiltrada = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date desde = sdf.parse(fechasFiltro[0]);
        Date hasta = sdf.parse(fechasFiltro[1]);
        for (Boleta boleta: listaBoletas) {
            Date fecha = sdf.parse(boleta.getFecha());
            if (fecha.compareTo(desde)>=0 && fecha.compareTo(hasta)<=0) listaFiltrada.add(boleta);
        }
        rvaBoletas.listaBoletas.clear();
        rvaBoletas.listaBoletas.addAll(listaFiltrada);
        rvaBoletas.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        dialogoCerrarSesion();
    }

    private void dialogoCerrarSesion() {
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