package com.example.heroproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EquipamentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        //Definim variables per mostrar valors a la pantalla
        String titol;
        int img, masArmadura, masAtaque, masVida, masVelocidad, preu;

        //Trobam els elements Xml pel seu id i els vinculam a una variable local Java
        TextView nom = findViewById(R.id.NomTextView);
        TextView armadura = findViewById(R.id.ArmaduraTextView);
        TextView atac = findViewById(R.id.AtacTextView);
        TextView vida = findViewById(R.id.VidaTextView);
        TextView velocitat = findViewById(R.id.VelocitatTextView);
        TextView preuItem = findViewById(R.id.Preu);
        ImageView foto = findViewById(R.id.Imatge);

        //Rebem l'objecte llançat a la LlistaActivity amb un getIntent
        Intent intent = getIntent();
        Item item = intent.getParcelableExtra("Objecte");

        //Assignam els valors de l'objecte rebut a les variables
        titol = item.getNom();
        img = item.getImg();
        masArmadura = item.getArmadura();
        masAtaque = item.getAtac();
        masVida = item.getVida();
        masVelocidad = item.getVelocitat();
        preu = item.getPreu();

        //Assignam els valors guardats als textView de l'xml
        nom.setText(titol);
        armadura.setText(String.valueOf(masArmadura));
        atac.setText(String.valueOf(masAtaque));
        vida.setText(String.valueOf(masVida));
        velocitat.setText(String.valueOf(masVelocidad));
        preuItem.setText(String.valueOf(preu));
        foto.setImageResource(img);

        //Definim el boto i amb un clickListener enviam l'objecte a la MainActivity amb un intent
        Button bt = findViewById(R.id.BotoAfegir);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),MainActivity.class);
                i.putExtra("nou", item);
                startActivity(i);
            }
        });
    }
}