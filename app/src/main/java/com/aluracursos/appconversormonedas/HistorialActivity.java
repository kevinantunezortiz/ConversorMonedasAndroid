package com.aluracursos.appconversormonedas;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistorialActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ResultadoAdapter adapter;
    private List<Resultado> listaResultados;
    private HistorialMonedas historialMonedas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        historialMonedas = new HistorialMonedas(getApplicationContext());
        listaResultados = historialMonedas.obtenerResultados();
        Toolbar toolbar = findViewById(R.id.toolbar2); // Asegúrate de tener un Toolbar con este ID en tu layout
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaResultados = historialMonedas.obtenerResultados();
        listaResultados.forEach(resultado -> Log.d("Resultadocf", resultado.toString()));
        adapter = new ResultadoAdapter(listaResultados);
        recyclerView.setAdapter(adapter);

        // Habilitar el botón de "Up" (flecha hacia atrás)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Historial");
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
            Drawable navigationIcon = toolbar.getNavigationIcon();
            if (navigationIcon != null) {
                navigationIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
                toolbar.setNavigationIcon(navigationIcon);
            }
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Cierra la Activity actual y vuelve a la anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}