package com.example.myplants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myplants.database.PlantaDAO;
import com.example.myplants.model.Planta;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaPlanta;
    private PlantaAdapter plantaAdapter;
    private PlantaDAO plantaDAO;

    private final ActivityResultLauncher<Intent> addPlantaLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    loadPlantas();  // Recarrega as plantas após salvar
                    Toast.makeText(this, "Planta salva", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPlanta = findViewById(R.id.lista_planta);
        plantaDAO = new PlantaDAO(this);

        loadPlantas();
    }

    private void loadPlantas() {
        List<Planta> plantas = plantaDAO.getAllPlantas();
        if (plantas != null && !plantas.isEmpty()) {
            plantaAdapter = new PlantaAdapter(this, plantas);
            listaPlanta.setAdapter(plantaAdapter);
        } else {
            Toast.makeText(this, "Nenhuma planta encontrada.", Toast.LENGTH_SHORT).show();
        }
    }

    public void addPlanta(View view) {
        Intent intent = new Intent(MainActivity.this, PlantaDetailActivity.class);
        addPlantaLauncher.launch(intent);
    }
}
