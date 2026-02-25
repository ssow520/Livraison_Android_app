package com.example.livraison;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Fragment pour afficher la liste des livreurs et leurs détails
public class LivreursFragment extends Fragment {

    private RecyclerView recyclerViewLivreurs; // Liste des livreurs
    private GridView gridViewDetails;          // Grille pour afficher les détails des abonnements
    private TextView txtDetails;               // Texte pour afficher message si pas de livraison

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // On associe le fragment à son layout
        View view = inflater.inflate(R.layout.fragment_livreurs, container, false);

        // Récupération des composants du layout
        recyclerViewLivreurs = view.findViewById(R.id.recyclerViewLivreurs);
        gridViewDetails = view.findViewById(R.id.gridViewDetails);
        txtDetails = view.findViewById(R.id.txtDetails);

        // Configuration de la RecyclerView (liste verticale)
        recyclerViewLivreurs.setLayoutManager(new LinearLayoutManager(getContext()));

        // Récupération de la liste des livreurs
        ArrayList<Livreur> livreurs = GestionnaireDonnees.getInstance().getLivreurs();

        // Création de l'adapter et définition du comportement au clic sur un livreur
        LivreurAdapter adapter = new LivreurAdapter(livreurs, new LivreurAdapter.OnLivreurClickListener() {
            @Override
            public void onLivreurClick(Livreur livreur) {
                afficherDetailsLivreur(livreur); // Affiche les abonnements du livreur sélectionné
            }
        });

        recyclerViewLivreurs.setAdapter(adapter);

        return view;
    }

    // Méthode pour afficher les détails d'un livreur
    private void afficherDetailsLivreur(Livreur livreur) {
        Route route = GestionnaireDonnees.getInstance().trouverRoute(livreur.getNumeroRoute());

        // Si la route n'existe pas ou n'a pas d'abonnements
        if (route == null || route.getAbonnements().isEmpty()) {
            txtDetails.setText("Aucune livraison pour ce livreur");
            txtDetails.setVisibility(View.VISIBLE);
            gridViewDetails.setVisibility(View.GONE);
            return;
        }

        // Sinon, on affiche les détails dans la grille
        txtDetails.setVisibility(View.GONE);
        gridViewDetails.setVisibility(View.VISIBLE);

        ArrayList<String> details = new ArrayList<>();
        for (Abonnement ab : route.getAbonnements()) {
            String detail = ab.getAdresse() + "\n" +
                    "Client: " + ab.getNomClient() + "\n" +
                    "Magazines: " + ab.getQuantiteMagazines() + "\n" +
                    "Journaux: " + ab.getQuantiteJournaux();
            details.add(detail);
        }

        // Création et application de l'adapter pour afficher les détails
        DetailsAdapter adapter = new DetailsAdapter(getContext(), details);
        gridViewDetails.setAdapter(adapter);
    }
}
