package com.example.souza.mutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Cadastro extends AppCompatActivity {

    private MutanteOperations mutanteDBOperations;
    String habilidades = "", aux;

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
        if(!habilidades.equals(""))
        habilidades = habilidades + ", " + aux;
        else
            habilidades = aux;
    }

    public void finalizar(View view) {

        Intent it = getIntent();
        Bundle params = it.getExtras();
        int aux2 = params.getInt("aux");
        int id = params.getInt("mutanteId");

        EditText nome = (EditText)findViewById(R.id.nome);

        if (aux2 == 0) {
            mutanteDBOperations.addMutante(nome.getText().toString(), habilidades);
        }
        if (aux2 == 1) {
            mutanteDBOperations.updateMutante(nome.getText().toString(), habilidades, id);
        }

        Intent it2 = new Intent(this, MainActivity.class);
        startActivity(it2);


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
