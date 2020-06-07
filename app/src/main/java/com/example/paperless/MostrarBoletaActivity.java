package com.example.paperless;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        info_boleta = getIntent().getStringExtra("info_boleta");
        es_nueva_boleta = getIntent().getBooleanExtra("es_nuevo_ingreso", false);

        if (es_nueva_boleta) {
            boton_listo.setVisibility(View.GONE);
            boton_confirmar.setVisibility(View.VISIBLE);
            boton_cancelar.setVisibility(View.VISIBLE);
        }

        tv_info_boleta.setText(info_boleta);
    }
}