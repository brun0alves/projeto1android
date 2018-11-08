package com.example.souza.mutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Cadastro extends AppCompatActivity {

    private MutanteOperations mutanteDBOperations;
    String habilidades ="", aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mutanteDBOperations = new MutanteOperations(this);
        mutanteDBOperations.open();
    }

    public void addHab(View view) {
        EditText habilidade = (EditText)findViewById(R.id.habilidade);
        aux=habilidade.getText().toString();
        habilidades= habilidades+aux;
    }

    public void finalizar(View view) {
        EditText nome = (EditText)findViewById(R.id.nome);
        mutanteDBOperations.addMutante(nome.getText().toString(), habilidades);
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);


    }

    @Override
    protected void onResume() {
        mutanteDBOperations.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mutanteDBOperations.close();
        super.onPause();
    }
}
