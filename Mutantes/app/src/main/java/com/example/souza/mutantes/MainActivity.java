package com.example.souza.mutantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void cadastrar(View view) {
        Intent it = new Intent(this, Cadastro.class);
        startActivity(it);
    }

    public void listar(View view) {
        Intent it2 = new Intent(MainActivity.this, Lista.class);
        startActivity(it2);

    }

    public void pesquisar(View view) {

    }

    public void sair(View view) {

    }
}
