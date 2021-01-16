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
    public TextView cash,atac,armadura,vida,velocitat;
    public ImageView casc, armor, boots, sword, secundaria;
    public int cost = 0, arm = 0, at = 0, vd = 0, vel = 0;
    public int imgCasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definim tots els elements xml que trobarem a la MainActivity
        casc = findViewById(R.id.imageViewCasco);
        armor = findViewById(R.id.imageViewArmor);
        boots = findViewById(R.id.imageViewBoots);
        sword = findViewById(R.id.imageViewSword);
        secundaria = findViewById(R.id.imageViewBallesta);

        cash = findViewById(R.id.textViewDineros);
        atac = findViewById(R.id.textViewAtac);
        armadura = findViewById(R.id.textViewDefensa);
        vida = findViewById(R.id.textViewVida);
        velocitat = findViewById(R.id.textViewVelocitat);

        //Aqui hi ha els listeners de les 5 imatges, que llançen un intent amb un codi per saber quin item s'ha pitjat. Obrin la LlistaActivity.
        clickListeners();

        //Cream un item persona amb el seu constructor
        persona = new Item(null,100,100,100,100,1000,R.drawable.casco,R.drawable.armadura,R.drawable.sword,R.drawable.ballesta,R.drawable.botes);

        //Cream un item objecte amb el seu constructor
        Item casco = new Item("casco 1", R.drawable.casco1, 0, 0, 0, 0, 100, 1);
        Item pit = new Item("armor 1", R.drawable.armadura1, 0, 0, 0, 0, 0,2);
        Item botes = new Item("botes 1", R.drawable.botes1, 0, 0, 0, 0, 200,3);
        Item espasa = new Item("espasa 1", R.drawable.espasa1, 0, 0, 0, 0, 0,4);
        Item secun = new Item("secun 1", R.drawable.pistola, 0, 0, 0, 0, 0,5);

        //Llista on ficarem els items equipats per calcular després els valors
        test = new ArrayList<>();
        test.add(casco);
        test.add(pit);
        test.add(botes);
        test.add(espasa);
        test.add(secun);

        sumarValorsItems();

        if (cost > 0)
        {
            persona.setPreu(persona.preu - cost);
        }else{
            persona.setPreu(persona.preu + cost);
        }
        persona.setPreu(persona.preu - cost);
        persona.setArmadura(persona.armadura + arm);
        persona.setAtac(persona.atac + at);
        persona.setVida(persona.vida + vd);
        persona.setVelocitat(persona.velocitat + vel);

        //Aqui assignam els valors de l'item persona als elements XML.
        assignarXml();

        //Aqui recuperam l'intent i l'objecte de la EquipamentActivity.
        //De prova, assignam el valor a l'armadura i a la foto.
        try
        {
            Intent intent = getIntent();
            Item obj = intent.getParcelableExtra("nou");

            int a = obj.getCodi();
            for (int i = 0; i < test.size() - 1; i++)
            {
                if (test.get(i).getCodi() == a)
                {
                    test.set(i,obj);
                    switch (a)
                    {
                        case 1:
                            persona.setImgCasc(obj.getImg());
                            break;
                        case 2:
                            persona.setImgArmadura(obj.getImg());
                            break;
                        case 3:
                            persona.setImgBotes(obj.getImg());
                            break;
                        case 4:
                            persona.setImgArma(obj.getImg());
                            break;
                        case 5:
                            persona.setImgSecundaria(obj.getImg());
                            break;
                    }
                }
            }

            sumarValorsItems();

            persona.setPreu(persona.preu - cost);
            persona.setArmadura(persona.armadura + arm);
            persona.setAtac(persona.atac + at);
            persona.setVida(persona.vida + vd);
            persona.setVelocitat(persona.velocitat + vel);

            assignarXml();


        }
        catch(Exception e)
        {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG);
        }

    }

    public void sumarValorsItems()
    {
        cost = 0; arm = 0; at = 0; vd = 0; vel = 0;

        for (int i = 0; i <= test.size() - 1; i++)
        {
            cost += test.get(i).getPreu();
            arm += test.get(i).getArmadura();
            at += test.get(i).getAtac();
            vd += test.get(i).getVida();
            vel += test.get(i).getVelocitat();
        }
    }


    public void assignarXml()
    {
        cash.setText("or" + persona.getPreu());
        atac.setText("at" + persona.getAtac());
        armadura.setText("ar" + persona.getArmadura());
        vida.setText("vd" + persona.getVida());
        velocitat.setText("vel" + persona.getVelocitat());
        casc.setImageResource(persona.getImgCasc());
        armor.setImageResource(persona.getImgArmadura());
        boots.setImageResource(persona.getImgBotes());
        sword.setImageResource(persona.getImgArma());
        secundaria.setImageResource(persona.getImgSecundaria());
    }

    public void clickListeners()
    {
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
    }
}