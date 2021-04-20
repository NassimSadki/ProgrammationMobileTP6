package com.example.bdd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Planete {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    private String nom;

    @ColumnInfo(name = "size")
    private int taille;

    private static final AtomicInteger count = new AtomicInteger(0);

    Planete(String nom, int taille){
        // auto increment de l'id
        this.uid = count.incrementAndGet();
        this.nom = nom;
        this.taille = taille;
    }

    public int getUid() {
        return uid;
    }

    public String getNom() {
        return nom;
    }

    public int getTaille() {
        return taille;
    }

}