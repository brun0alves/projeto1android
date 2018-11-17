package com.example.souza.mutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    int aux = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void cadastrar(View view) {
        Intent it = new Intent(this, CadastroActivity.class);
        Bundle params = new Bundle();
        params.putInt("aux", aux);
        it.putExtras(params);
        startActivity(it);
    }

    public void listar(View view) {
        Intent it2 = new Intent(MainActivity.this, ListaActivity.class);
        startActivity(it2);

    }

    public void pesquisar(View view) {
        Intent it = new Intent(MainActivity.this, BuscaActivity.class);
        startActivity(it);
    }

    public void sair(View view) {
        finish();
        System.exit(0);
    }
}
