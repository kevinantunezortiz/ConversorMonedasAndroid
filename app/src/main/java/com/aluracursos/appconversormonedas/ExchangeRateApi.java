package com.aluracursos.appconversormonedas;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExchangeRateApi {
    @GET("v6/{apikey}/pair/{from}/{to}/{amount}")
    Call<Resultado> getExchangeRate(
            @Path("apikey") String apiKey,
            @Path("from") String fromCurrency,
            @Path("to") String toCurrency,
            @Path("amount") String amount
    );
}