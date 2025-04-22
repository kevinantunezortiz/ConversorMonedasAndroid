package com.aluracursos.appconversormonedas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ResultadoAdapter extends RecyclerView.Adapter<ResultadoAdapter.ResultadoViewHolder> {

    private List<Resultado> resultados;

    public ResultadoAdapter(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    @NonNull
    @Override
    public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_resultado, parent, false);
        return new ResultadoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int position) {
        Resultado resultado = resultados.get(position);
        holder.tvValor.setText("Valor: " + String.format("%.2f", resultado.getValor()));
        holder.tvTotal.setText("Total: " + String.format("%.2f", resultado.getTotal()));
        holder.tvMoneda1.setText("Moneda 1: " + resultado.getMoneda1());
        holder.tvMoneda2.setText("Moneda 2: " + resultado.getMoneda2());
        holder.tvCantidad.setText("Cantidad: " + resultado.getCantidad());
        holder.tvFechaHora.setText("Fecha/Hora: " + resultado.getFechaHoraActual());
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    public static class ResultadoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvValor;
        public TextView tvTotal;
        public TextView tvMoneda1;
        public TextView tvMoneda2;
        public TextView tvCantidad;
        public TextView tvFechaHora;

        public ResultadoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvValor = itemView.findViewById(R.id.tvValor);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvMoneda1 = itemView.findViewById(R.id.tvMoneda1);
            tvMoneda2 = itemView.findViewById(R.id.tvMoneda2);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvFechaHora = itemView.findViewById(R.id.tvFechaHora);
        }
    }
}
