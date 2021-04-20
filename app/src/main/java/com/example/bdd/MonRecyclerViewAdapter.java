package com.example.bdd;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MonRecyclerViewAdapter extends RecyclerView.Adapter<MonRecyclerViewAdapter.ConteneurDeDonnee> {
    private ArrayList<Planete> planetes;

    public MonRecyclerViewAdapter(ArrayList<Planete> planetes) {
        this.planetes = planetes;
    }

    @NonNull
    @Override
    public ConteneurDeDonnee onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ConteneurDeDonnee(view);
    }

    @Override
    public void onBindViewHolder(ConteneurDeDonnee conteneur, int position) {
        conteneur.tv_principal.setText(planetes.get(position).getNom());
        conteneur.tv_auxiliaire.setText(planetes.get(position).getTaille()+"");
        switch (planetes.get(position).getNom()) {
            case "Mercure":
                conteneur.imageView.setImageResource(R.drawable.mercure);
                break;
            case "Venus":
                conteneur.imageView.setImageResource(R.drawable.venus);
                break;
            case "Terre":
                conteneur.imageView.setImageResource(R.drawable.terre);
                break;
            case "Mars":
                conteneur.imageView.setImageResource(R.drawable.terre);
                break;
            case "Jupiter":
                conteneur.imageView.setImageResource(R.drawable.terre);
                break;
            case "Saturne":
                conteneur.imageView.setImageResource(R.drawable.terre);
                break;
            case "Uranus":
                conteneur.imageView.setImageResource(R.drawable.terre);
                break;
            case "Neptune":
                conteneur.imageView.setImageResource(R.drawable.terre);
                break;
            case "Pluton":
                conteneur.imageView.setImageResource(R.drawable.terre);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return planetes.size();
    }

    public  static class ConteneurDeDonnee extends RecyclerView.ViewHolder {
        TextView tv_principal;
        TextView tv_auxiliaire;
        ImageView imageView;

        public ConteneurDeDonnee(View itemView) {
            super(itemView);
            tv_principal = (TextView) itemView.findViewById(R.id.tv_principal);
            tv_auxiliaire = (TextView) itemView.findViewById(R.id.tv_auxiliaire);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }

}
