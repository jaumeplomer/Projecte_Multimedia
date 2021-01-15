package com.example.heroproject;

import android.os.Parcel;
import android.os.Parcelable;

//Classe d'objecte genèrica
//La parcelablam
public class Item implements Parcelable {

    String nom;
    int img, armadura, atac, vida, velocitat, preu, codi, imgCasc, imgArmadura, imgArma, imgSecundaria, imgBotes;

    //Constructor a utilitzar per els items
    public Item(String nom, int img, int armadura, int atac, int vida, int velocitat, int preu, int codi) {
        this.nom = nom;
        this.img = img;
        this.armadura = armadura;
        this.atac = atac;
        this.vida = vida;
        this.velocitat = velocitat;
        this.preu = preu;
        this.codi = codi;
    }

    //Constructor a utilitzar per la persona
    public Item(String nom, int armadura, int atac, int vida, int velocitat, int preu, int imgCasc, int imgArmadura, int imgArma, int imgSecundaria, int imgBotes) {
        this.nom = nom;
        this.armadura = armadura;
        this.atac = atac;
        this.vida = vida;
        this.velocitat = velocitat;
        this.preu = preu;
        this.imgCasc = imgCasc;
        this.imgArmadura = imgArmadura;
        this.imgArma = imgArma;
        this.imgSecundaria = imgSecundaria;
        this.imgBotes = imgBotes;
    }

    protected Item(Parcel in) {
        nom = in.readString();
        img = in.readInt();
        armadura = in.readInt();
        atac = in.readInt();
        vida = in.readInt();
        velocitat = in.readInt();
        preu = in.readInt();
        codi = in.readInt();
        imgCasc = in.readInt();
        imgArmadura = in.readInt();
        imgArma = in.readInt();
        imgSecundaria = in.readInt();
        imgBotes = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeInt(img);
        dest.writeInt(armadura);
        dest.writeInt(atac);
        dest.writeInt(vida);
        dest.writeInt(velocitat);
        dest.writeInt(preu);
        dest.writeInt(codi);
        dest.writeInt(imgCasc);
        dest.writeInt(imgArmadura);
        dest.writeInt(imgArma);
        dest.writeInt(imgSecundaria);
        dest.writeInt(imgBotes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getAtac() {
        return atac;
    }

    public void setAtac(int atac) {
        this.atac = atac;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    public int getPreu() {
        return preu;
    }

    public void setPreu(int preu) {
        this.preu = preu;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }
    public int getImgCasc() {
        return imgCasc;
    }

    public void setImgCasc(int imgCasc) {
        this.imgCasc = imgCasc;
    }

    public int getImgArmadura() {
        return imgArmadura;
    }

    public void setImgArmadura(int imgArmadura) {
        this.imgArmadura = imgArmadura;
    }

    public int getImgArma() {
        return imgArma;
    }

    public void setImgArma(int imgArma) {
        this.imgArma = imgArma;
    }

    public int getImgSecundaria() {
        return imgSecundaria;
    }

    public void setImgSecundaria(int imgSecundaria) {
        this.imgSecundaria = imgSecundaria;
    }

    public int getImgBotes() {
        return imgBotes;
    }

    public void setImgBotes(int imgBotes) {
        this.imgBotes = imgBotes;
    }
}
