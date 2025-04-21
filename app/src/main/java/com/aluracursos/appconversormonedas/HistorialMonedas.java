package com.aluracursos.appconversormonedas;

import android.content.Context; // Necesitas el Context para acceder a los directorios de la aplicación
import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;

public class HistorialMonedas {
    private Gson gson = new Gson();
    private Context context;
    private static final String NOMBRE_ARCHIVO = "historial.txt";

    // Necesitas pasar el Context de tu Activity o Application al instanciar esta clase
    public HistorialMonedas(Context context) {
        this.context = context;
    }

    public void guardarResultado(Resultado resultado) {
        try (FileOutputStream fileOutputStream = context.openFileOutput(NOMBRE_ARCHIVO, Context.MODE_APPEND);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
            String json = gson.toJson(resultado);
            System.out.println(json);
            bufferedWriter.write(json + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar en el archivo: " + e.getMessage(), e);
        }
    }

    public ArrayList<Resultado> obtenerResultados() {
        ArrayList<Resultado> resultados = new ArrayList<>();
        try (FileInputStream fileInputStream = context.openFileInput(NOMBRE_ARCHIVO);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                Resultado resultado = gson.fromJson(json, Resultado.class);
                resultados.add(resultado);
            }
        } catch (FileNotFoundException e) {
            // El archivo aún no existe, no hay resultados para leer
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return resultados;
    }
}