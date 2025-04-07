package com.example.myplants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myplants.database.PlantaDAO;
import com.example.myplants.model.Planta;

public class PlantaDetailActivity extends AppCompatActivity {

    private EditText nomeEditText, especieEditText, quantidadeEditText, alturaEditText;
    private CheckBox toxicaCheckBox;
    private PlantaDAO plantaDAO;
    private long plantaId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantadetail);

        nomeEditText = findViewById(R.id.edit_nome);
        especieEditText = findViewById(R.id.edit_especie);
        quantidadeEditText = findViewById(R.id.edit_quantidade);
        alturaEditText = findViewById(R.id.edit_altura);
        toxicaCheckBox = findViewById(R.id.check_toxica);

        plantaDAO = new PlantaDAO(this);

        // Recupera o ID da planta da Intent
        plantaId = getIntent().getLongExtra("plantaId", -1);

        // Se o ID for válido, carregamos os dados da planta
        if (plantaId != -1) {
            Planta planta = plantaDAO.getPlantaById(plantaId);
            if (planta != null) {
                nomeEditText.setText(planta.getNome());
                especieEditText.setText(planta.getEspecie());
                quantidadeEditText.setText(String.valueOf(planta.getQuantidade()));
                alturaEditText.setText(String.valueOf(planta.getAltura()));
                toxicaCheckBox.setChecked(planta.isToxica());
            }
        }
    }

    public void savePlanta(View view) {
        String nome = nomeEditText.getText().toString();
        String especie = especieEditText.getText().toString();
        int quantidade = Integer.parseInt(quantidadeEditText.getText().toString());
        double altura = Double.parseDouble(alturaEditText.getText().toString());
        boolean toxica = toxicaCheckBox.isChecked();

        // Criamos um novo objeto Planta com o ID e dados fornecidos
        Planta planta = new Planta(plantaId, nome, especie, quantidade, altura, toxica);

        boolean success;
        if (plantaId == -1) {
            // Se o ID for -1, é uma nova planta
            success = plantaDAO.insert(planta) != -1;
        } else {
            // Caso contrário, estamos atualizando a planta existente
            success = plantaDAO.update(planta);
        }

        if (success) {
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Erro ao salvar planta", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
