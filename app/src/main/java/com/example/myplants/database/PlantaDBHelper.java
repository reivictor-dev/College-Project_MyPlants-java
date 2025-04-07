package com.example.myplants.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlantaDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "catalogo_plantas.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "plantas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_ESPECIE = "especie";
    public static final String COLUMN_QUANTIDADE = "quantidade";
    public static final String COLUMN_ALTURA = "altura";
    public static final String COLUMN_TOXICA = "toxica";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT NOT NULL, " +
                    COLUMN_ESPECIE + " TEXT NOT NULL, " +
                    COLUMN_QUANTIDADE + " INTEGER, " +
                    COLUMN_ALTURA + " REAL, " +
                    COLUMN_TOXICA + " INTEGER)";

    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public PlantaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }
}
