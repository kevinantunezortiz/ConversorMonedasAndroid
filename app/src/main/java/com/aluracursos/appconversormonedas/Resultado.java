package com.aluracursos.appconversormonedas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Resultado {
    @Expose
    @SerializedName("conversion_rate")
    private double valor;
    @Expose @SerializedName("conversion_result")
    private double total;
    private String moneda1,moneda2,cantidad,fechaHoraActual;
    public Resultado(double valor, double total) {
        this.valor = valor;
        this.total = total;
    }

    public String getFechaHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        this.fechaHoraActual = sdf.format(new Date());
        return fechaHoraActual;
    }


    public double getValor() {
        return valor;
    }

    public double getTotal() {
        return total;
    }

    public void setMoneda1(String moneda1) {
        this.moneda1 = moneda1;
    }

    public void setMoneda2(String moneda2) {
        this.moneda2 = moneda2;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMoneda2() {
        return moneda2;
    }
    public String getCantidad() {
        return cantidad;
    }
    public String getMoneda1() {
        return moneda1;
    }
}
