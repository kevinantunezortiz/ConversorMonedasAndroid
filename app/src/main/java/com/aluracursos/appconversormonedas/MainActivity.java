package com.aluracursos.appconversormonedas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ApiMonedas apiMonedas = new ApiMonedas();
    Spinner comboMonedas1;
    Spinner comboMonedas2;

    EditText txtCantidad;

    TextView txtValor, txtTotal;
    HashMap<String, String> monedas;

    Button btnConvertir, btnHistorial;

    HistorialMonedas historialMonedas ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        historialMonedas = new HistorialMonedas(getApplicationContext());
        txtCantidad = findViewById(R.id.txtCantidad);
        comboMonedas1 = findViewById(R.id.comboMonedas1);
        monedas = apiMonedas.obtenerMonedas();
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, apiMonedas.obtenerMonedas().keySet().toArray(new String[0]));
        // Asigna el adaptador al Spinner
        comboMonedas1.setAdapter(adaptador);
        comboMonedas1.setSelection(3);
        comboMonedas2 = findViewById(R.id.comboMonedas2);
        comboMonedas2.setAdapter(adaptador);
        comboMonedas2.setSelection(0);
        txtValor = findViewById(R.id.txtValor);
        txtTotal = findViewById(R.id.txtTotal);
        btnConvertir = findViewById(R.id.btnConvertir);
        btnConvertir.setOnClickListener(v -> convertirMonedas());
        btnHistorial = findViewById(R.id.btnHistorial);
        btnHistorial.setOnClickListener(v -> mostrarHistorial());
    }
    public void mostrarHistorial(){
        Intent intent = new Intent(this, HistorialActivity.class);
        startActivity(intent);
    }
    public void convertirMonedas(){
        String moneda1 = comboMonedas1.getSelectedItem().toString();
        String moneda2 = comboMonedas2.getSelectedItem().toString();
        String cantidad = txtCantidad.getText().toString();
        String moneda1Codigo = monedas.get(moneda1);
        String moneda2Codigo = monedas.get(moneda2);
        if(cantidad.isEmpty()){
            Toast toast = Toast.makeText(this, "Ingrese una cantidad", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            if(cantidad.equals(".")){
                Toast.makeText(this, "La cantidad no puede ser solo un punto", Toast.LENGTH_SHORT).show();
            }else{


                apiMonedas.ObtenerResultado(moneda1Codigo, moneda2Codigo, cantidad , new ApiMonedas.ResultadoCallback() {
                    @Override
                    public void onResultadoObtenido(Resultado resultado) {
                        Log.d("Resultado", resultado.toString());
                        txtValor.setText(String.format("1 %s = %s", moneda1Codigo, resultado.getValor()));
                        txtTotal.setText(String.format("Total: %s %s", resultado.getTotal(), moneda2Codigo));
                        resultado.setMoneda1(moneda1Codigo+"-"+moneda1);
                        resultado.setMoneda2(moneda2Codigo+"-"+moneda2);
                        resultado.setCantidad(cantidad);
                        historialMonedas.guardarResultado(resultado);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("Error", t.getMessage());
                    }
                });
            }
        }
    }
}