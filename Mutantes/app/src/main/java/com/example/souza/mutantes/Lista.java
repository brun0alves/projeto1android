package com.example.souza.mutantes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Lista extends Activity {

    private ListView list;
    private MutanteOperations mutanteOperations;
    ArrayList<Mutante> mutantes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        MutanteOperations mutanteOperations = new MutanteOperations(this);
        mutanteOperations.open();
        List values = mutanteOperations.getAllMutante();
        mutantes = (ArrayList<Mutante>) values;
        list = montaLista(Lista.this,this, values);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {


                int mutanteId = (int) mutantes.get(arg2).getId();
                Intent it = new Intent(Lista.this, Detalhes.class);
                Bundle params = new Bundle();
                params.putInt("mutanteId", mutanteId);
                it.putExtras(params);
                startActivity(it);

            };


    });
    };

    public static ListView montaLista(Context context, Activity activity, List values){
        ListView list = (ListView) activity.findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);
        return list;
    }
}