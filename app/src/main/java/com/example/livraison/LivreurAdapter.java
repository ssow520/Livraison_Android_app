package com.example.livraison;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Adapter pour afficher la liste des livreurs dans un RecyclerView
public class LivreurAdapter extends RecyclerView.Adapter<LivreurAdapter.LivreurViewHolder> {

    private ArrayList<Livreur> livreurs; // Liste des livreurs à afficher
    private OnLivreurClickListener listener; // Écouteur pour gérer les clics sur un livreur

    // Interface pour gérer le clic sur un élément de la liste
    public interface OnLivreurClickListener {
        void onLivreurClick(Livreur livreur);
    }

    // Constructeur : reçoit la liste de livreurs et l'écouteur de clic
    public LivreurAdapter(ArrayList<Livreur> livreurs, OnLivreurClickListener listener) {
        this.livreurs = livreurs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LivreurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // On "inflate" le layout pour chaque élément de la liste
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livreur, parent, false);
        return new LivreurViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LivreurViewHolder holder, int position) {
        Livreur livreur = livreurs.get(position);
        // On affiche le nom du livreur
        holder.txtNomLivreur.setText(livreur.getNom());

        // On gère le clic sur un élément
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLivreurClick(livreur);
            }
        });
    }

    @Override
    public int getItemCount() {
        return livreurs.size(); // Nombre d'éléments à afficher
    }

    // Classe interne représentant chaque élément de la liste
    static class LivreurViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomLivreur;

        public LivreurViewHolder(@NonNull View itemView) {
            super(itemView);
            // On récupère la référence du TextView dans le layout item_livreur.xml
            txtNomLivreur = itemView.findViewById(R.id.txtNomLivreur);
        }
    }
}
