package com.example.paperless;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.paperless.entidadesbd.Boleta;

import java.util.ArrayList;
import java.util.List;

public class RVAdapterBoletas extends Adapter<RVAdapterBoletas.BoletasViewHolder> implements Filterable {

    ArrayList<Boleta> listaBoletas;
    ArrayList<Boleta> listaBoletasCompleta;

    public RVAdapterBoletas(ArrayList<Boleta> listaBoletas) {
        this.listaBoletas = listaBoletas;
        this.listaBoletasCompleta = new ArrayList<>(listaBoletas);
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

    @Override
    public Filter getFilter() {
        return  filtroPorNombreComercio;
    }

    private Filter filtroPorNombreComercio = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Boleta> listaBoletasFiltrada = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                listaBoletasFiltrada.addAll(listaBoletasCompleta);
            } else {
                String patronFiltrado = constraint.toString().toLowerCase().trim();
                for (Boleta boleta: listaBoletasCompleta) {
                    if (boleta.getNombreComercio().toLowerCase().contains(patronFiltrado))
                        listaBoletasFiltrada.add(boleta);
                }
            }
            FilterResults resultadoFiltro = new FilterResults();
            resultadoFiltro.values = listaBoletasFiltrada;
            return resultadoFiltro;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaBoletas.clear();
            listaBoletas.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };



}
