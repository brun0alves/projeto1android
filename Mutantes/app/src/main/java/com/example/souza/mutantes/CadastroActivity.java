package com.example.souza.mutantes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private MutanteOperations mutanteDBOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        EditText habilidade = (EditText) findViewById(R.id.habilidade);
        EditText nome = (EditText) findViewById(R.id.nome);
        Intent it = getIntent();
        Bundle params = it.getExtras();
        int id = params.getInt("mutanteId");
        mutanteDBOperations = new MutanteOperations(this);
        mutanteDBOperations.open();
        if(id != 0) {
            habilidade.setText(mutanteDBOperations.getMutanteById(id).getHabilidade(), TextView.BufferType.EDITABLE);
            nome.setText(mutanteDBOperations.getMutanteById(id).getNome(), TextView.BufferType.EDITABLE);
        }
    }

    public void finalizar(View view) {
        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            Intent it = getIntent();
            Bundle params = it.getExtras();
            int aux2 = params.getInt("aux");
            int id = params.getInt("mutanteId");

            EditText nome = (EditText) findViewById(R.id.nome);
            EditText habilidade = (EditText) findViewById(R.id.habilidade);
            if (!(habilidade.getText().toString().equals("")|| nome.getText().toString().equals(""))) {
                if (aux2 == 0) {
                    List<Mutante> mutantes = mutanteDBOperations.getAllMutante();
                    for (Mutante m : mutantes) {
                        if (m.getNome().equalsIgnoreCase(nome.getText().toString())) {
                            String e = "Mutante já cadastrado!";
                            throw new Exception(e);
                        }
                    }
                    mutanteDBOperations.addMutante(nome.getText().toString(), habilidade.getText().toString());
                }
                if (aux2 == 1) {
                    List<Mutante> mutantes = mutanteDBOperations.getAllMutante();
                    for (Mutante m : mutantes) {
                        if (m.getNome().equalsIgnoreCase(nome.getText().toString()) && m.getId() != id) {
                            String e = "Mutante já cadastrado!";
                            throw new Exception(e);
                        }
                    }
                    mutanteDBOperations.updateMutante(nome.getText().toString(), habilidade.getText().toString(), id);
                }
                builder.setTitle("Mutante Cadastrado!");
                builder.setMessage("Cadastro do mutante " + nome.getText().toString() + " foi efetuado com sucesso!");
                alerta = builder.create();
                alerta.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                });
                alerta.show();
            } else {
                String e = "Preencha todos os dados!";
                throw new Exception(e);
            }
        } catch (Exception e) {
            builder.setTitle("Um erro ocorreu!");
            builder.setMessage("Não foi possível cadastrar o mutante." + "\n" + e.getMessage());
            alerta = builder.create();
            alerta.show();

        }
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
