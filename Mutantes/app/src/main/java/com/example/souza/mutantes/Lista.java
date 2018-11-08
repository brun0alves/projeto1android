package com.example.souza.mutantes;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Lista extends Activity {

    ListView list;
    private MutanteOperations mutanteDBOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        mutanteDBOperations = new MutanteOperations(this);
        mutanteDBOperations.open();

        List values = mutanteDBOperations.getAllMutante();
        list = (ListView) findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);

    }

}
