package com.example.myplants.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myplants.model.Planta;

import java.util.ArrayList;
import java.util.List;

public class PlantaDAO {

    private SQLiteDatabase db;
    private PlantaDBHelper dbHelper;

    public PlantaDAO(Context context) {
        dbHelper = new PlantaDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Planta planta) {
        ContentValues values = new ContentValues();
        values.put(PlantaDBHelper.COLUMN_NOME, planta.getNome());
        values.put(PlantaDBHelper.COLUMN_ESPECIE, planta.getEspecie());
        values.put(PlantaDBHelper.COLUMN_QUANTIDADE, planta.getQuantidade());
        values.put(PlantaDBHelper.COLUMN_ALTURA, planta.getAltura());
        values.put(PlantaDBHelper.COLUMN_TOXICA, planta.isToxica() ? 1 : 0);

        long id = db.insert(PlantaDBHelper.TABLE_NAME, null, values);
        Log.d("PlantaDAO", "Planta inserida com ID: " + id);
        return id;
    }
    public List<Planta> getAllPlantas() {
        List<Planta> plantas = new ArrayList<>();
        Cursor cursor = db.query(PlantaDBHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Verifique se as colunas existem
                int idIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_ID);
                int nomeIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_NOME);
                int especieIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_ESPECIE);
                int quantidadeIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_QUANTIDADE);
                int alturaIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_ALTURA);
                int toxicaIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_TOXICA);

                if (idIndex >= 0 && nomeIndex >= 0 && especieIndex >= 0 && quantidadeIndex >= 0 && alturaIndex >= 0 && toxicaIndex >= 0) {
                    Planta planta = new Planta(
                            cursor.getLong(idIndex),
                            cursor.getString(nomeIndex),
                            cursor.getString(especieIndex),
                            cursor.getInt(quantidadeIndex),
                            cursor.getDouble(alturaIndex),
                            cursor.getInt(toxicaIndex) == 1
                    );
                    plantas.add(planta);
                } else {
                    Log.e("PlantaDAO", "Erro ao acessar uma ou mais colunas. Índices inválidos.");
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        Log.d("PlantaDAO", "Total de plantas carregadas: " + plantas.size());
        return plantas;
    }
    public Planta getPlantaById(long id) {
        Planta planta = null;
        String[] columns = {
                PlantaDBHelper.COLUMN_ID,
                PlantaDBHelper.COLUMN_NOME,
                PlantaDBHelper.COLUMN_ESPECIE,
                PlantaDBHelper.COLUMN_QUANTIDADE,
                PlantaDBHelper.COLUMN_ALTURA,
                PlantaDBHelper.COLUMN_TOXICA
        };

        String selection = PlantaDBHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(PlantaDBHelper.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_ID);
            int nomeIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_NOME);
            int especieIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_ESPECIE);
            int quantidadeIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_QUANTIDADE);
            int alturaIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_ALTURA);
            int toxicaIndex = cursor.getColumnIndex(PlantaDBHelper.COLUMN_TOXICA);

            if (idIndex >= 0 && nomeIndex >= 0 && especieIndex >= 0 && quantidadeIndex >= 0 && alturaIndex >= 0 && toxicaIndex >= 0) {
                planta = new Planta(
                        cursor.getLong(idIndex),
                        cursor.getString(nomeIndex),
                        cursor.getString(especieIndex),
                        cursor.getInt(quantidadeIndex),
                        cursor.getDouble(alturaIndex),
                        cursor.getInt(toxicaIndex) == 1
                );
            } else {
                Log.e("PlantaDAO", "Erro ao acessar uma ou mais colunas. Índices inválidos.");
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        return planta;
    }

    public boolean update(Planta planta) {
        ContentValues values = new ContentValues();
        values.put(PlantaDBHelper.COLUMN_NOME, planta.getNome());
        values.put(PlantaDBHelper.COLUMN_ESPECIE, planta.getEspecie());
        values.put(PlantaDBHelper.COLUMN_QUANTIDADE, planta.getQuantidade());
        values.put(PlantaDBHelper.COLUMN_ALTURA, planta.getAltura());
        values.put(PlantaDBHelper.COLUMN_TOXICA, planta.isToxica() ? 1 : 0);

        int rowsAffected = db.update(PlantaDBHelper.TABLE_NAME, values, PlantaDBHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(planta.getId())});
        Log.d("PlantaDAO", "Número de linhas atualizadas: " + rowsAffected);
        return rowsAffected > 0;
    }

    public void delete(long plantaId) {
        db.delete("plantas", "id = ?", new String[]{String.valueOf(plantaId)});
        Log.d("PlantaDAO", "Planta com ID " + plantaId + " excluída.");
    }
    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
            Log.d("PlantaDAO", "Banco de dados fechado.");
        }
    }
}
