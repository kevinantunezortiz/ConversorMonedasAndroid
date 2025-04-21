package com.aluracursos.appconversormonedas;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMonedas {
    private String apikey = "f04170f78a1f513606b22a0b";
    private ExchangeRateApi apiService;

    public ApiMonedas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://v6.exchangerate-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ExchangeRateApi.class);
    }

    public void ObtenerResultado(String moneda1, String moneda2, String cantidad,final ResultadoCallback callback){

        Call<Resultado> call = apiService.getExchangeRate(apikey, moneda1, moneda2, cantidad);
        call.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    callback.onResultadoObtenido(resultado);
                } else {
                    callback.onError(new Exception("Error en la respuesta: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
    public interface ResultadoCallback {
        void onResultadoObtenido(Resultado resultado);
        void onError(Throwable t);
    }

    public HashMap<String,String> obtenerMonedas(){
        var monedas = new HashMap<String, String>();
        monedas.put("Peso Mexicano", "MXN");
        monedas.put("Dólar Estadounidense", "USD");
        monedas.put("Euro", "EUR");
        monedas.put("Peso Argentino", "ARS");
        monedas.put("Real Brasileño", "BRL");
        monedas.put("Peso Colombiano", "COP");
        return monedas;
    }
}
