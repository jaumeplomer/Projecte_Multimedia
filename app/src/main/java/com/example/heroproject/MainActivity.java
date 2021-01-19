package com.example.heroproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditNameFragment.EditNameDialogListener {

    public static int Pastatotal;
    public Item persona, casco, pit, botes, espasa, secun;
    public static ArrayList<Item> test = new ArrayList<>();
    public TextView cash,atac,armadura,vida,velocitat, nom;
    public ImageView casc, armor, boots, sword, secundaria, pers;
    public Button home, dona, reset;
    public int cost = 0, arm = 0, at = 0, vd = 0, vel = 0;
    public static int contador = 0;
    private static String nomPersona = "Hero 1";

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
        pers = findViewById(R.id.imageViewPersona);

        cash = findViewById(R.id.textViewDineros);
        atac = findViewById(R.id.textViewAtac);
        armadura = findViewById(R.id.textViewDefensa);
        vida = findViewById(R.id.textViewVida);
        velocitat = findViewById(R.id.textViewVelocitat);
        nom = findViewById(R.id.textViewNom);

        dona = findViewById(R.id.buttonDona);
        home = findViewById(R.id.buttonHome);
        reset = findViewById(R.id.buttonReset);

        //Aqui hi ha els listeners de les 5 imatges, que llançen un intent amb un codi per saber quin item s'ha pitjat. Obrin la LlistaActivity.
        clickListeners();

        //Aqui hi ha els listeners dels botons per triar el gènere del personatge
        botonsGenere();

        //Cridam el mètode per resetear
        botoReset();

        //Cream un item persona amb el seu constructor
        persona = new Item(null,0,20,100,30,20000,R.drawable.persona2);
        pers.setImageResource(persona.getImgPersona());

        //Cream cinc item objecte amb el seu constructor
        casco = new Item("casco 1", R.drawable.casco, 0, 0, 0, 0, 0, 1);
        pit = new Item("armor 1", R.drawable.armadura, 0, 0, 0, 0, 0,2);
        botes = new Item("botes 1", R.drawable.botes, 0, 0, 0, 0, 0,3);
        espasa = new Item("espasa 1", R.drawable.sword, 0, 0, 0, 0, 0,4);
        secun = new Item("secun 1", R.drawable.ballesta, 0, 0, 0, 0, 0,5);

        //Ficam els items a la llista d'objectes equipats. Utilitzam un contador perque només s'executi una vegada.
        if (contador == 0)
        {
            showEditDialog();
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

        //Cridam el mètode per recuperar els nous objectes que equipem.
        recuperarObjecte();
    }

    //COMENTAR
    private void showEditDialog()
    {
        FragmentManager fm = getSupportFragmentManager();
        EditNameFragment editNameFragment = EditNameFragment.newInstance("Some title");
        editNameFragment.show(fm,"fragment_edit_name");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        nomPersona = inputText;
        nom.setText(nomPersona);
    }


    //Segons el boto que pitjam carrega una imatge o una altre a la posició de la persona
    public void botonsGenere()
    {
        dona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persona.setImgPersona(R.drawable.persona2);
                pers.setImageResource(persona.getImgPersona());
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persona.setImgPersona(R.drawable.persona);
                pers.setImageResource(persona.getImgPersona());
            }
        });

    }

    //COMENTARIO
    public void botoReset()
    {
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                test.removeAll(test);
                test.add(casco);
                test.add(pit);
                test.add(botes);
                test.add(espasa);
                test.add(secun);

                //Cridam els mètodes per sumar valors, assigar-los a l'Item persona, i assignar-los als elements XML.
                sumarValorsItems();
                assignarPersona();
                assignarXml();
            }
        });
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
            cost += test.get(i).getPreu();
            arm += test.get(i).getArmadura();
            at += test.get(i).getAtac();
            vd += test.get(i).getVida();
            vel += test.get(i).getVelocitat();
        }
    }

    public void assignarPersona()
    {
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
        nom.setText(nomPersona);

        casc.setImageResource(persona.getImgCasc());
        armor.setImageResource(persona.getImgArmadura());
        boots.setImageResource(persona.getImgBotes());
        sword.setImageResource(persona.getImgArma());
        secundaria.setImageResource(persona.getImgSecundaria());
        pers.setImageResource(persona.getImgPersona());

        Pastatotal = persona.getPreu() - cost;
    }

    //Segons la imatge clicada, llança l'intent amb un codi que recuperarem per carregar una llista o una altre
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