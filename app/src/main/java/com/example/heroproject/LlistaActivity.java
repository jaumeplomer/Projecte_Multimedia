package com.example.heroproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class LlistaActivity extends AppCompatActivity {

    //Definim el listView de l'XML, 5 arraylist de tipus Item per omplir amb objectes, i un adaptador.
    private ListView llista;

    private ArrayList<Item> cascos = new ArrayList<>();
    private ArrayList<Item> botes = new ArrayList<>();
    private ArrayList<Item> armadures = new ArrayList<>();
    private ArrayList<Item> espases = new ArrayList<>();
    private ArrayList<Item> secundaria = new ArrayList<>();

    public Adaptador adaptador;

    //Aquest element es el que rebem amb l'intent de la MainActivity.
    Bundle codi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista);

        //Agafam el codi que rebem amb l'intent per omplir una llista o una altre. Aquest codi ens diu si s'ha pitjat una imatge o una altre.
        codi = getIntent().getExtras();
        int codiObtingut = codi.getInt("codi");
        llista = findViewById(R.id.llistaItems);

        //En funcio de la imatge pitjada, carregarem items a una llista o a l'altre, i la mostrarem amb l'Adaptador.
        switch(codiObtingut) {
            case 1:
                //afegim objectes a la llista d'Items cascos
                cascos.add(new Item("casco 1", R.drawable.casco1, 50, 0, 10, 0, 150,1));
                cascos.add(new Item("casco 2", R.drawable.casco, 60, 0, 0, 0, 100,1));

                //feim l'adapter i omplim la llista
                adaptador = new Adaptador(this,R.layout.item_llista, cascos);
                llista.setAdapter(adaptador);

                llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(view.getContext(), EquipamentActivity.class);
                        i.putExtra("Objecte",cascos.get(position));
                        startActivity(i);
                    }
                });
                break;
            //Feim el mateix que al case 1 per a tots els case
            case 2:
                armadures.add(new Item("armor 1", R.drawable.armadura1, 200, 0, 10, 0, 300,2));
                armadures.add(new Item("armor 2", R.drawable.armadura, 250, 0, 20, 0, 400,2));
                adaptador = new Adaptador(this,R.layout.item_llista, armadures);
                llista.setAdapter(adaptador);

                llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(view.getContext(), EquipamentActivity.class);
                        i.putExtra("Objecte",armadures.get(position));
                        startActivity(i);
                    }
                });
                break;
            case 3:
                botes.add(new Item("botes 1", R.drawable.botes1, 20, 0, 0, 50, 75,3));
                botes.add(new Item("botes 2", R.drawable.botes, 30, 0, 0, 45, 95,3));
                adaptador = new Adaptador(this, R.layout.item_llista, botes);
                llista.setAdapter(adaptador);

                llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(view.getContext(), EquipamentActivity.class);
                        i.putExtra("Objecte",botes.get(position));
                        startActivity(i);
                    }
                });
                break;
            case 4:
                espases.add(new Item("espasa 1", R.drawable.espasa1, 0, 100, 0, 50, 500,4));
                espases.add(new Item("espasa 2", R.drawable.sword, 0, 50, 0, 0, 200,4));
                adaptador = new Adaptador(this, R.layout.item_llista, espases);
                llista.setAdapter(adaptador);

                llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(view.getContext(), EquipamentActivity.class);
                        i.putExtra("Objecte",espases.get(position));
                        startActivity(i);
                    }
                });
                break;
            case 5:
                secundaria.add(new Item("secun 1", R.drawable.pistola, 0, 100, 0, 0, 100,5));
                secundaria.add(new Item("secun 2", R.drawable.ballesta, 0, 75, 0, 0, 100,5));
                adaptador = new Adaptador(this, R.layout.item_llista, secundaria);
                llista.setAdapter(adaptador);

                llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(view.getContext(), EquipamentActivity.class);
                        i.putExtra("Objecte",secundaria.get(position));
                        startActivity(i);
                    }
                });
                break;
        }
    }
}
