package com.example.souza.mutantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.souza.mutantes.MutanteBDWrapper.MUTANTE;

public class MutanteOperations {

    private MutanteBDWrapper dbHelper;
    private String[] MUTANTE_TABLE_COLUMNS = {MutanteBDWrapper.MUTANTE_ID, MutanteBDWrapper.MUTANTE_NOME, MutanteBDWrapper.MUTANTE_HABILIDADE};
    private SQLiteDatabase database;

    public MutanteOperations(Context context) {
        dbHelper = new MutanteBDWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Mutante addMutante(String nome, String habilidade) {
        ContentValues values = new ContentValues();
        values.put(MutanteBDWrapper.MUTANTE_NOME, nome);
        values.put(MutanteBDWrapper.MUTANTE_HABILIDADE, habilidade);
        long mutId = database.insert(MUTANTE, null, values);
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTE,
                MUTANTE_TABLE_COLUMNS, MutanteBDWrapper.MUTANTE_ID + " = "
                        + mutId, null, null, null, null);
        cursor.moveToFirst();
        Mutante newComment = parseMutante(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteMutante(int id) {
        System.out.println("Removido id: " + id);
        database.delete(MutanteBDWrapper.MUTANTE, MutanteBDWrapper.MUTANTE_ID
                + " = " + id, null);
    }

    public void updateMutante(String nome, String habilidade, int id) {
        ContentValues values = new ContentValues();
        values.put(MutanteBDWrapper.MUTANTE_NOME, nome);
        values.put(MutanteBDWrapper.MUTANTE_HABILIDADE, habilidade);
        database.update(MutanteBDWrapper.MUTANTE, values, MutanteBDWrapper.MUTANTE_ID + " = "
                        + id, null);

    }

    public Mutante getMutanteById(int id) {
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTE, MUTANTE_TABLE_COLUMNS, MutanteBDWrapper.MUTANTE_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();


        Mutante hab = parseMutante(cursor);
        cursor.close();
        return hab;
    }

    public List getAllMutante() {
        List mutantes = new ArrayList();
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTE,
                MUTANTE_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    public List getMutanteByName(String name){
        List mutantes = new ArrayList();
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTE,
                MUTANTE_TABLE_COLUMNS, MutanteBDWrapper.MUTANTE_NOME +" LIKE ?", new String[]{ "%" + name + "%"}, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    public List getMutanteByHabilidade(String habilidade){
        List mutantes = new ArrayList();
        Cursor cursor = database.query(MutanteBDWrapper.MUTANTE,
                MUTANTE_TABLE_COLUMNS, MutanteBDWrapper.MUTANTE_HABILIDADE +" = ?", new String[]{ habilidade }, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mutante mutante = parseMutante(cursor);
            mutantes.add(mutante);
            cursor.moveToNext();
        }
        cursor.close();
        return mutantes;
    }

    private Mutante parseMutante(Cursor cursor) {
        Mutante mutante = new Mutante();
        mutante.setId(cursor.getInt(0));
        mutante.setNome(cursor.getString(1));
        mutante.setHabilidade(cursor.getString(2));
        return mutante;
    }

}
