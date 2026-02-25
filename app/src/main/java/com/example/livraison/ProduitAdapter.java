package com.example.livraison;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Adapter pour afficher la liste des produits dans une RecyclerView
public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.ProduitViewHolder> {

    private ArrayList<String> produits;           // Liste des noms de produits (ex: "Magazines", "Journaux")
    private OnProduitClickListener listener;      // Interface pour gérer le clic sur un produit

    // Interface pour détecter le clic sur un produit
    public interface OnProduitClickListener {
        void onProduitClick(String produit);
    }

    // Constructeur de l'adapter
    public ProduitAdapter(ArrayList<String> produits, OnProduitClickListener listener) {
        this.produits = produits;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProduitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // On crée la vue pour chaque élément de la liste
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produit, parent, false);
        return new ProduitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitViewHolder holder, int position) {
        // On récupère le produit à la position donnée
        String produit = produits.get(position);
        holder.txtNomProduit.setText(produit); // On met le nom du produit dans le TextView

        // Détection du clic sur l'élément
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onProduitClick(produit); // Appelle la méthode de l'interface
            }
        });
    }

    @Override
    public int getItemCount() {
        return produits.size(); // Nombre total d'éléments dans la liste
    }

    // Classe interne pour représenter un élément de la RecyclerView
    static class ProduitViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomProduit; // TextView pour afficher le nom du produit

        public ProduitViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomProduit = itemView.findViewById(R.id.txtNomProduit); // On relie le TextView du layout
        }
    }
}
