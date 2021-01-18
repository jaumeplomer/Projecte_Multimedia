package com.example.heroproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends ArrayAdapter<Item> {
    private Context context;
    private List<Item> items;

    //Guardam a variables els elements que rebem per constructor.
    public Adaptador(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //Agafam una Item i segons la seva posició a l'array "l'inflam" amb el layout creat anteriorment.
        Item item = items.get(position);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_llista, null);

        //Guardam els elements XML segons el seu id
        TextView textTittle = view.findViewById(R.id.titol);
        TextView textArmor = view.findViewById(R.id.categoria1);
        TextView textAtac = view.findViewById(R.id.categoria2);
        TextView textVida = view.findViewById(R.id.categoria3);
        TextView textVelo = view.findViewById(R.id.categoria4);
        TextView textPreu = view.findViewById(R.id.preu);
        ImageView imageView = view.findViewById(R.id.imageView);

        //Els omplim amb els getters de la classe Item.
        textTittle.setText("Nom: " + item.getNom());
        textArmor.setText("Armadura: +" + item.getArmadura());
        textAtac.setText("Atac: +" + item.getAtac());
        textVida.setText("Vida: +" + item.getVida());
        textVelo.setText("Velocitat: +" + item.getVelocitat());
        textPreu.setText("Preu: " + item.getPreu());
        imageView.setImageResource(item.getImg());

        //Retornam la vista que hem omplit.
        return view;
    }
}