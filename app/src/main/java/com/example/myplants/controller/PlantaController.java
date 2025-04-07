package com.example.myplants.controller;

import android.content.Context;

import com.example.myplants.database.PlantaDAO;
import com.example.myplants.model.Planta;

import java.util.List;

public class PlantaController {

    private PlantaDAO plantaDAO;

    public PlantaController(Context context) {
        plantaDAO = new PlantaDAO(context);
    }

    public List<Planta> getAllPlantas() {
        return plantaDAO.getAllPlantas();
    }

    public void savePlanta(Planta planta) {
        plantaDAO.insert(planta);
    }

    public void updatePlanta(Planta planta) {
        plantaDAO.update(planta);
    }

    public void getPlantaById(int id) {
        plantaDAO.getPlantaById(id);
    }
    public void deletePlanta(int id) {
        plantaDAO.delete(id);
    }
}