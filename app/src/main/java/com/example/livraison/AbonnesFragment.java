package com.example.livraison;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

// Fragment qui affiche la liste des abonnés dans une grille
public class AbonnesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // On crée la vue du fragment à partir du layout fragment_abonnes.xml
        View view = inflater.inflate(R.layout.fragment_abonnes, container, false);

        // On récupère la GridView où on va afficher les abonnés
        GridView gridViewAbonnes = view.findViewById(R.id.gridViewAbonnes);

        // On prépare une liste de chaînes de texte pour afficher les détails de chaque abonnement
        ArrayList<String> details = new ArrayList<>();

        // On parcourt tous les abonnements stockés dans le Gestionnaire de données
        for (Abonnement ab : GestionnaireDonnees.getInstance().getAbonnements()) {
            // On construit une chaîne avec toutes les informations de l'abonné
            String detail = "Client: " + ab.getNomClient() + "\n" +
                    "Adresse: " + ab.getAdresse() + "\n" +
                    "Magazines: " + ab.getQuantiteMagazines() + "\n" +
                    "Journaux: " + ab.getQuantiteJournaux() + "\n" +
                    "Route: " + ab.getNumeroRoute();
            // On ajoute cette chaîne à la liste
            details.add(detail);
        }

        // On crée un adapter pour afficher les détails dans la GridView
        DetailsAdapter adapter = new DetailsAdapter(getContext(), details);
        gridViewAbonnes.setAdapter(adapter); // On associe l'adapter à la GridView

        // On retourne la vue du fragment
        return view;
    }
}
