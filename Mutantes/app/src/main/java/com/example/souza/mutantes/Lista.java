package com.example.souza.mutantes;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {


                int mutanteId = arg2 + 1;
                Intent it = new Intent(Lista.this, Detalhes.class);
                Bundle params = new Bundle();
                params.putInt("mutanteId", mutanteId);
                it.putExtras(params);
                startActivity(it);

            };


    });
    };
}