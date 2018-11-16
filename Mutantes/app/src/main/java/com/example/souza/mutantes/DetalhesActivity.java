package com.example.souza.mutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetalhesActivity extends AppCompatActivity {

    private MutanteOperations mutanteDBOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
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

        Intent it2 = new Intent(this, CadastroActivity.class);
        Bundle params2 = new Bundle();
        params2.putInt("mutanteId", mutanteId);
        params2.putInt("aux", aux);
        it2.putExtras(params2);
        startActivity(it2);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mutanteDBOperations = new MutanteOperations(this);
        mutanteDBOperations.open();

        Intent it = getIntent();
        Bundle params = it.getExtras();
        int mutanteId = params.getInt("mutanteId");

        Mutante mutante = mutanteDBOperations.getMutanteById(mutanteId);
        TextView nome = (TextView) findViewById(R.id.nome);
        TextView habilidade = (TextView) findViewById(R.id.hab);
        habilidade.setText(mutante.getHabilidade());
        nome.setText(mutante.getNome());
    }
}