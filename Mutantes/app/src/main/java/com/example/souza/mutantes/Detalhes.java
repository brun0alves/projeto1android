package com.example.souza.mutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Detalhes extends AppCompatActivity {

    private MutanteOperations mutanteDBOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        mutanteDBOperations = new MutanteOperations(this);
        mutanteDBOperations.open();

        Intent it = getIntent();
        Bundle params = it.getExtras();
        int mutanteId = params.getInt("mutanteId");

        Mutante hab = mutanteDBOperations.getMutanteById(mutanteId);

        TextView habilidade = (TextView) findViewById(R.id.hab);
        habilidade.setText(hab.toString());
    }

    public void deletar (View view) {
        Intent it = getIntent();
        Bundle params = it.getExtras();
        int mutanteId = params.getInt("mutanteId");
        mutanteDBOperations.deleteMutante(mutanteId);

    }

    public void editar (View view) {
        int aux = 1;

        Intent it = getIntent();
        Bundle params = it.getExtras();
        int mutanteId = params.getInt("mutanteId");

        Intent it2 = new Intent(this, Cadastro.class);
        Bundle params2 = new Bundle();
        params2.putInt("mutanteId", mutanteId);
        params2.putInt("aux", aux);
        it.putExtras(params2);
        startActivity(it2);

    }
}
