package com.example.heroproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int Pastatotal;
    public Item persona, casco, pit, botes, espasa, secun;
    public static ArrayList<Item> test = new ArrayList<>();
    public TextView cash,atac,armadura,vida,velocitat;
    public ImageView casc, armor, boots, sword, secundaria;
    public int cost = 0;
    public int arm = 0;
    public int at = 0;
    public int vd = 0;
    public int vel = 0;
    public static int contador = 0;

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
        persona = new Item(null,0,20,100,30,200);

        //Cream cinc item objecte amb el seu constructor
        casco = new Item("casco 1", R.drawable.casco, 0, 0, 0, 0, 0, 1);
        pit = new Item("armor 1", R.drawable.armadura, 0, 0, 0, 0, 0,2);
        botes = new Item("botes 1", R.drawable.botes, 0, 0, 0, 0, 0,3);
        espasa = new Item("espasa 1", R.drawable.sword, 0, 0, 0, 0, 0,4);
        secun = new Item("secun 1", R.drawable.ballesta, 0, 0, 0, 0, 0,5);

        //Ficam els items a la llista d'objectes equipats. Utilitzam un contador perque només s'executi una vegada.

        if (contador == 0)
        {
            test.add(casco);
            test.add(pit);
            test.add(botes);
            test.add(espasa);
            test.add(secun);
            contador++;
            //Cridam els mètodes per sumar valors, assigar-los a l'Item persona, i assignar-los als elements XML.
            sumarValorsItems();
            assignarPersona();
            assignarXml();
        }

        //Dialog per mostrar el nom dels items equipats per fer comprovacions
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("OnCreate");
        builder.setMessage(test.get(0).nom + " || " + test.get(1).nom);
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();*/

        //Cridam el mètode per recuperar els nous objectes que equipem.
        recuperarObjecte();
    }

    public void recuperarObjecte()
    {
        try
        {
            //Rebem l'objecte a equipar mitjançant l'intent
            Intent intent = getIntent();
            Item obj = intent.getParcelableExtra("nouItem");

            //Agafam el seu codi, i recorrem la llista d'objectes equipats per saber amb quin es correspon
            int a = obj.getCodi();
            for (int i = 0; i < test.size() - 1; i++)
            {
                //Quan trobam el correspost, canviam el nou item per l'antic.
                if (test.get(i).getCodi() == a)
                {
                    test.set(i,obj);
                }
            }
            //Actualitzam els valors amb els tres mètodes.
            sumarValorsItems();
            assignarPersona();
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
        //Amb les variables iniciades a 0, recorrem la llista i sumam tots els valors iguals a una variable.
        for (int i = 0; i <= test.size() - 1; i++)
        {
            cost = cost + test.get(i).getPreu();
            arm += test.get(i).getArmadura();
            at += test.get(i).getAtac();
            vd += test.get(i).getVida();
            vel += test.get(i).getVelocitat();
        }
    }

    public void assignarPersona()
    {
        //Utilitzam les variable del mètode sumarValorsItems() per posar els valors de l'Item persona bé.
        /*
        persona.setPreu(persona.preu - cost);
        persona.setArmadura(persona.armadura + arm);
        persona.setAtac(persona.atac + at);
        persona.setVida(persona.vida + vd);
        persona.setVelocitat(persona.velocitat + vel);
        */

        //Assignam les imatges segons la posició que ocupen a l'array.
        persona.setImgCasc(test.get(0).getImg());
        persona.setImgArmadura(test.get(1).getImg());
        persona.setImgBotes(test.get(2).getImg());
        persona.setImgArma(test.get(3).getImg());
        persona.setImgSecundaria(test.get(4).getImg());
    }

    public void assignarXml()
    {
        //Assignam als elements XML el valor corresponent de l'Item persona.
        cash.setText("Or: " + (persona.getPreu() - cost));
        atac.setText("Atac: " + (persona.getAtac() + at));
        armadura.setText("Armadura: " + (persona.getArmadura() + arm));
        vida.setText("Vida: " + (persona.getVida() + vd));
        velocitat.setText("Velocitat: " + (persona.getVelocitat() + vel));

        casc.setImageResource(persona.getImgCasc());
        armor.setImageResource(persona.getImgArmadura());
        boots.setImageResource(persona.getImgBotes());
        sword.setImageResource(persona.getImgArma());
        secundaria.setImageResource(persona.getImgSecundaria());

        Pastatotal = persona.getPreu() - cost;
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