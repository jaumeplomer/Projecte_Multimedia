package com.example.heroproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Item persona;
    public ArrayList<Item> test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definim tots els elements xml que trobarem a la MainActivity
        ImageView casc = findViewById(R.id.imageViewCasco);
        ImageView armor = findViewById(R.id.imageViewArmor);
        ImageView boots = findViewById(R.id.imageViewBoots);
        ImageView sword = findViewById(R.id.imageViewSword);
        ImageView secundaria = findViewById(R.id.imageViewBallesta);
        TextView cash = findViewById(R.id.textViewDineros);
        TextView atac = findViewById(R.id.textViewAtac);
        TextView armadura = findViewById(R.id.textViewDefensa);
        TextView vida = findViewById(R.id.textViewVida);
        TextView velocitat = findViewById(R.id.textViewVelocitat);


        //Cream un item persona amb el seu constructor
        persona = new Item(null,0,20,100,50,2000,R.drawable.casco,R.drawable.armadura,R.drawable.sword,R.drawable.ballesta,R.drawable.botes);

        //Cream un item objecte amb el seu constructor
        Item casco = new Item("casco 1", R.drawable.casco1, 50, 0, 10, 0, 150, 1);
        Item pit = new Item("armor 1", R.drawable.armadura1, 200, 0, 10, 0, 300,2);
        Item botes = new Item("botes 1", R.drawable.botes1, 20, 0, 0, 50, 75,3);
        Item espasa = new Item("espasa 1", R.drawable.espasa1, 0, 100, 0, 50, 500,4);
        Item secun = new Item("secun 1", R.drawable.pistola, 0, 100, 0, 0, 100,5);

        //Llista on ficarem els items equipats per calcular després els valors
        test = new ArrayList<>();
        test.add(casco);
        test.add(pit);
        test.add(botes);
        test.add(espasa);
        test.add(secun);

        int cost = 0;
        for (int i = 0; i <= test.size() - 1; i++)
        {
            cost += test.get(i).getPreu();
        }
        persona.setPreu(persona.preu - cost);

        //Aqui assignam els valors de l'item persona als elements XML.
        cash.setText("or" + persona.getPreu());
        atac.setText("at" + persona.getAtac());
        armadura.setText("ar" + persona.getArmadura());
        vida.setText("vida" + persona.getVida());
        velocitat.setText("vel" + persona.getVelocitat());

        //Aqui hi ha els listeners de les 5 imatges, que llançen un intent amb un codi per saber quin item s'ha pitjat. Obrin la LlistaActivity.
        casc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LlistaActivity.class);
                intent.putExtra("codi", 1);
                startActivity(intent);
            }
        });

        armor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LlistaActivity.class);
                intent.putExtra("codi", 2);
                startActivity(intent);
            }
        });

        boots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LlistaActivity.class);
                intent.putExtra("codi", 3);
                startActivity(intent);
            }
        });

        sword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),LlistaActivity.class);
                intent.putExtra("codi", 4);
                startActivity(intent);
            }
        });

        secundaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),LlistaActivity.class);
                intent.putExtra("codi", 5);
                startActivity(intent);
            }
        });


        //Aqui recuperam l'intent i l'objecte de la EquipamentActivity.
        //De prova, assignam el valor a l'armadura i a la foto.
        try
        {
            Intent intent = getIntent();
            Item obj = intent.getParcelableExtra("nou");

            /*for(int i = 0; i <= test.size(); i++)
            {
               /* if (test.get(i).getCodi() == obj.codi)
                {
                    test.remove(test.get(i));
                    test.add(obj);
                }*/

            armadura.setText(String.valueOf(persona.getArmadura() + obj.getArmadura()));
            //test.add(obj);
            casc.setImageResource(obj.getImg());

        }
        catch(Exception e)
        {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG);
        }

    }
}