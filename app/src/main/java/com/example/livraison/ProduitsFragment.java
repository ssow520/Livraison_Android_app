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

// Fragment pour afficher les produits et les abonnements associés
public class ProduitsFragment extends Fragment {

    private RecyclerView recyclerViewProduits;        // Liste des produits ("Magazines", "Journaux")
    private GridView gridViewDetailsProduits;         // Détails des abonnements pour le produit sélectionné
    private TextView txtDetailsProduits;              // Message quand il n'y a aucun abonnement

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Création de la vue du fragment
        View view = inflater.inflate(R.layout.fragment_produits, container, false);

        recyclerViewProduits = view.findViewById(R.id.recyclerViewProduits);
        gridViewDetailsProduits = view.findViewById(R.id.gridViewDetailsProduits);
        txtDetailsProduits = view.findViewById(R.id.txtDetailsProduits);

        // Layout vertical pour la RecyclerView
        recyclerViewProduits.setLayoutManager(new LinearLayoutManager(getContext()));

        // Liste des produits disponibles
        ArrayList<String> produits = new ArrayList<>();
        produits.add("Magazines");
        produits.add("Journaux");

        // Création de l'adapter pour afficher les produits
        ProduitAdapter adapter = new ProduitAdapter(produits, new ProduitAdapter.OnProduitClickListener() {
            @Override
            public void onProduitClick(String produit) {
                // Affiche les détails quand un produit est cliqué
                afficherDetailsProduit(produit);
            }
        });
        recyclerViewProduits.setAdapter(adapter);

        return view;
    }

    // Méthode pour afficher les abonnements liés à un produit
    private void afficherDetailsProduit(String produit) {
        ArrayList<String> details = new ArrayList<>();

        // Parcours de tous les abonnements
        for (Abonnement ab : GestionnaireDonnees.getInstance().getAbonnements()) {
            boolean inclure = false;

            // Vérifie si l'abonnement contient le produit sélectionné
            if (produit.equals("Magazines") && ab.getQuantiteMagazines() > 0) {
                inclure = true;
            } else if (produit.equals("Journaux") && ab.getQuantiteJournaux() > 0) {
                inclure = true;
            }

            if (inclure) {
                Route route = GestionnaireDonnees.getInstance().trouverRoute(ab.getNumeroRoute());
                String nomLivreur = (route != null && route.getNomLivreur() != null) ? route.getNomLivreur() : "Pas de livreur";

                // Prépare le texte de détail pour l'affichage
                String detail = "Adresse: " + ab.getAdresse() + "\n" +
                        "Client: " + ab.getNomClient() + "\n" +
                        "Livreur: " + nomLivreur;
                details.add(detail);
            }
        }

        // Si aucun abonnement pour ce produit
        if (details.isEmpty()) {
            txtDetailsProduits.setText("Aucun abonnement pour ce produit");
            txtDetailsProduits.setVisibility(View.VISIBLE);
            gridViewDetailsProduits.setVisibility(View.GONE);
        } else {
            // Sinon on affiche les détails dans le GridView
            txtDetailsProduits.setVisibility(View.GONE);
            gridViewDetailsProduits.setVisibility(View.VISIBLE);

            DetailsAdapter adapter = new DetailsAdapter(getContext(), details);
            gridViewDetailsProduits.setAdapter(adapter);
        }
    }
}
