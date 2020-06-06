package com.example.paperless;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;

public class RVAdapterBoletas extends Adapter<RVAdapterBoletas.BillsViewHolder> {

    ArrayList<String[]> listaBoletas;

    public RVAdapterBoletas(ArrayList<String[]> listaBoletas) {
        this.listaBoletas = listaBoletas;
    }

    @NonNull
    @Override
    public BillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_boletas, null, false);
        return new BillsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillsViewHolder holder, int position) {
        holder.asignarDatos(listaBoletas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaBoletas.size();
    }

    public class BillsViewHolder extends RecyclerView.ViewHolder {
        TextView nombreComercio, fecha;
        public BillsViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreComercio = itemView.findViewById(R.id.item_nombre_comercio);
            fecha = itemView.findViewById(R.id.item_fecha);
        }

        public void asignarDatos(String[] datos) {
            nombreComercio.setText(datos[0]);
            fecha.setText(datos[1]);
        }
    }

}
