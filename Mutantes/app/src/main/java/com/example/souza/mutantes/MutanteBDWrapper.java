package com.example.souza.mutantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MutanteBDWrapper extends SQLiteOpenHelper{
    public static final String MUTANTE = "Mutante";
    public static final String MUTANTE_ID = "_id";
    public static final String MUTANTE_NOME = "_nome";
    public static final String MUTANTE_HABILIDADE = "_habilidade";
    private static final String DATABASE_NAME = "Mutante.db";
    private static final int DATABASE_VERSION = 1;
    // creation SOLite statement
    private static final String DATABASE_CREATE = "create table " + MUTANTE + "(" + MUTANTE_ID + " integer primary key autoincrement, "
            + MUTANTE_NOME + " text not null, " + MUTANTE_HABILIDADE + " text not null);";

    public MutanteBDWrapper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MUTANTE);
        onCreate(db);
    }
}
