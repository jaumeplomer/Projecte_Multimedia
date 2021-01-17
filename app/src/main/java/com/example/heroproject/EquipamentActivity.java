package com.example.heroproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EquipamentActivity extends AppCompatActivity {

    //Definim variables per mostrar valors a la pantalla
    public TextView nom, armadura, atac, vida, velocitat, preuItem;
    public ImageView foto;
    public String titol;
    public int img, masArmadura, masAtaque, masVida, masVelocidad, preu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        //Trobam els elements Xml pel seu id i els vinculam a una variable local Java
        nom = findViewById(R.id.NomTextView);
        armadura = findViewById(R.id.ArmaduraTextView);
        atac = findViewById(R.id.AtacTextView);
        vida = findViewById(R.id.VidaTextView);
        velocitat = findViewById(R.id.VelocitatTextView);
        preuItem = findViewById(R.id.PreuTextView);
        foto = findViewById(R.id.Imatge);

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
        nom.setText("Nom: " + titol);
        armadura.setText("Armadura: " + masArmadura);
        atac.setText("Atac: " + masAtaque);
        vida.setText("Vida: " + masVida);
        velocitat.setText("Velocitat: " + masVelocidad);
        preuItem.setText("Preu: " + preu);
        foto.setImageResource(img);

        //Definim el boto i amb un clickListener enviam l'objecte a la MainActivity amb un intent
        Button bt = findViewById(R.id.BotoAfegir);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (preu > MainActivity.Pastatotal)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("UPS");
                    builder.setMessage("No tens diners suficients");
                    builder.setNegativeButton("Torna", null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                 {
                    Intent i = new Intent(v.getContext(),MainActivity.class);
                    i.putExtra("nouItem", item);
                    startActivity(i);
                }
            }
        });
    }
}