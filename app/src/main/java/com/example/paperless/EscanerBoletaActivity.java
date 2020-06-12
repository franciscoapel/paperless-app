package com.example.paperless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.paperless.entidadesbd.Boleta;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class EscanerBoletaActivity extends AppCompatActivity {

    private CodeScanner code_scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear_boleta);

        CodeScannerView sv_escaner = findViewById(R.id.sv_escaner_boleta_activity);
        code_scanner = new CodeScanner(this, sv_escaner);
        code_scanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(EscanerBoletaActivity.this,
                                MostrarBoletaActivity.class);
                        i.putExtra(Boleta.INFO, result.getText());
                        i.putExtra("es_nuevo_ingreso", true);
                        startActivity(i);
                    }
                });
            }
        });
        sv_escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code_scanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pedirPermisosCamara();
    }

    @Override
    protected void onPause() {
        code_scanner.releaseResources();
        super.onPause();
    }

    private void pedirPermisosCamara() {
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                code_scanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(EscanerBoletaActivity.this, "Es necesario dar permisos" +
                        "para el uso de la camara", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest,
                                                           PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}