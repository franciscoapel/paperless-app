package com.example.paperless;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.paperless.entidadesbd.Boleta;

import java.util.ArrayList;

public class RVAdapterBoletas extends Adapter<RVAdapterBoletas.BoletasViewHolder> {

    ArrayList<Boleta> listaBoletas;

    public RVAdapterBoletas(ArrayList<Boleta> listaBoletas) {
        this.listaBoletas = listaBoletas;
    }

    @NonNull
    @Override
    public BoletasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_boletas_activity, null, false);
        return new BoletasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoletasViewHolder holder, final int position) {
        holder.asignarDatos(listaBoletas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MostrarBoletaActivity.class);
                i.putExtra(Boleta.INFO, listaBoletas.get(position).getInfoBoleta());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaBoletas.size();
    }

    public class BoletasViewHolder extends RecyclerView.ViewHolder {
        TextView nombreComercio, fecha;
        public BoletasViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreComercio = itemView.findViewById(R.id.item_nombre_comercio);
            fecha = itemView.findViewById(R.id.item_fecha);
        }

        public void asignarDatos(Boleta boleta) {
            nombreComercio.setText(boleta.getNombreComercio());
            fecha.setText(boleta.getFecha());
        }
    }

}
