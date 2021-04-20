package com.example.bdd;

import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AjouterPlaneteFragment extends Fragment {

    public AjouterPlaneteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ajouter_planete, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        EditText ajouterNom = view.findViewById(R.id.ajouterNom);
        EditText ajouterTaille = view.findViewById(R.id.ajouterTaille);
        Button boutonAjouter = view.findViewById(R.id.boutonAjouter);
        boutonAjouter.setOnClickListener(v -> {
                MainActivity activity = (MainActivity) getActivity();
                Planete planete = new Planete(ajouterNom.getText().toString(),
                        Integer.parseInt(ajouterTaille.getText().toString()));
                activity.insert(planete);

                // on ferme le Fragment une fois la planete ajoutée
                getFragmentManager().beginTransaction().remove(AjouterPlaneteFragment.this).commit();
                // on recharge l'activité pour ajuster l'affichage
                activity.restart();
        });
    }
}