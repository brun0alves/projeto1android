package com.example.souza.mutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

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
        if (!(habilidades.equals("") || nome.getText().toString().equals(""))) {
            if (aux2 == 0) {
                List<Mutante> mutantes = mutanteDBOperations.getAllMutante();
                for (Mutante m : mutantes) {
                    if (m.getNome().equalsIgnoreCase(nome.getText().toString())){
                        Toast.makeText(this, "Nome de mutante já cadastrado!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mutanteDBOperations.addMutante(nome.getText().toString(), habilidades);
            }
            if (aux2 == 1) {
                List<Mutante> mutantes = mutanteDBOperations.getAllMutante();
                for (Mutante m : mutantes) {
                    if (m.getNome().equalsIgnoreCase(nome.getText().toString())){
                        Toast.makeText(this, "Nome de mutante já cadastrado!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mutanteDBOperations.updateMutante(nome.getText().toString(), habilidades, id);
            }
            finish();
        }
        else
            Toast.makeText(this, "Preencha todos os dados e adicione habilidades!", Toast.LENGTH_SHORT).show();
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
