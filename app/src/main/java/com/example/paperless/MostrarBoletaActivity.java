package com.example.paperless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paperless.entidadesbd.BDHBoletas;
import com.example.paperless.entidadesbd.Boleta;

public class MostrarBoletaActivity extends AppCompatActivity {

    String info_boleta;
    Boolean es_nueva_boleta;

    LinearLayout linearlayout_info_boleta;
    TextView tv_info_boleta;
    Button boton_listo, boton_confirmar, boton_cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_boleta);

        linearlayout_info_boleta = findViewById(R.id.linearlayout_mostrar_boleta_activity);
        tv_info_boleta = new TextView(this);
        linearlayout_info_boleta.addView(tv_info_boleta);

        boton_listo = findViewById(R.id.boton_listo_mostar_boleta_activity);
        boton_confirmar = findViewById(R.id.boton_confirmar_mostrar_boleta_activity);
        boton_cancelar = findViewById(R.id.boton_cancelar_mostrar_boleta_activity);

        info_boleta = getIntent().getStringExtra(Boleta.INFO);
        es_nueva_boleta = getIntent().getBooleanExtra("es_nuevo_ingreso", false);

        if (es_nueva_boleta) {
            boton_listo.setVisibility(View.GONE);
            boton_confirmar.setVisibility(View.VISIBLE);
            boton_cancelar.setVisibility(View.VISIBLE);
        }
        String[] valores = info_boleta.split(";");
        for (int i=0; i< Boleta.CAMPOS.length; i++) {
            imprimir_campo(Boleta.CAMPOS[i], valores[i]);
        }
    }

    public void confirmarBoleta(View view){
        String[] valores = info_boleta.split(";");
        BDHBoletas bdHelper = new BDHBoletas
                (this, BDHBoletas.NOMBRE_BD_BOLETAS, null, 1);
        SQLiteDatabase bdBoletas = bdHelper.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        for (int i=0; i<valores.length; i++) {
            cValues.put(BDHBoletas.CAMPOS[i], valores[i]);
        }
        if (bdBoletas.insert(BDHBoletas.NOMBRE_TABLA_BOLETAS, null, cValues)==-1)
            Toast.makeText(this, "Folio repetido, imposible agregar boleta.",
                    Toast.LENGTH_SHORT).show();
        bdBoletas.close();
        startActivity(new Intent(this, BoletasActivity.class));
    }

    public void volver(View view) {
        startActivity(new Intent(this, BoletasActivity.class));
    }

    private void imprimir_campo(String campo, String valor) {
        int tamanoLetra, colorLetra;
        tamanoLetra = 16;
        colorLetra = Color.WHITE;
        LinearLayout campoCompleto = new LinearLayout(this);
        TextView tvCampo, tvSeparador, tvValor;
        tvCampo = new TextView(this);
        tvCampo.setWidth(dpToPixel(94));
        tvCampo.setTextSize(tamanoLetra);
        tvCampo.setTextColor(colorLetra);
        tvCampo.setText(campo);
        tvSeparador = new TextView(this);
        tvSeparador.setWidth(dpToPixel(10));
        tvSeparador.setTextSize(tamanoLetra);
        tvSeparador.setTextColor(colorLetra);
        tvSeparador.setText(":");
        tvValor = new TextView(this);
        tvValor.setWidth(dpToPixel(200));
        tvValor.setGravity(Gravity.END);
        tvValor.setTextSize(tamanoLetra);
        tvValor.setTextColor(colorLetra);
        tvValor.setText(valor);

        campoCompleto.addView(tvCampo);
        campoCompleto.addView(tvSeparador);
        campoCompleto.addView(tvValor);
        linearlayout_info_boleta.addView(campoCompleto);
    }

    private int dpToPixel(int dp) {
        return (int)(dp*(getResources().getDisplayMetrics().density)+0.5f);}

}