package com.example.souza.mutantes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class BuscaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String selectedText;
    ArrayList<Mutante> mutantes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opcoes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void buscar(View view) {
        EditText params = (EditText) findViewById(R.id.params);
        if (selectedText.equals("Nome")) {
            MutanteOperations mutanteOperations = new MutanteOperations(this);
            mutanteOperations.open();
            List values = mutanteOperations.getMutanteByName(params.getText().toString());
            mutantes = (ArrayList<Mutante>) values;
            ListView list = (ListView) this.findViewById(R.id.searchList);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
            list.setAdapter(adapter);
        } else {
            MutanteOperations mutanteOperations = new MutanteOperations(this);
            mutanteOperations.open();
            List values = mutanteOperations.getMutanteByHabilidade(params.getText().toString());
            mutantes = (ArrayList<Mutante>) values;
            ListView list = (ListView) this.findViewById(R.id.searchList);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
            list.setAdapter(adapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedText = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
