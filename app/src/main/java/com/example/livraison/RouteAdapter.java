package com.example.livraison;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Adapter pour afficher les routes dans un RecyclerView
public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

    private ArrayList<Route> routes; // Liste des routes à afficher
    private OnRouteClickListener listener; // Écouteur pour gérer les clics sur une route

    // Interface pour écouter le clic sur une route
    public interface OnRouteClickListener {
        void onRouteClick(Route route);
    }

    // Constructeur : on passe la liste des routes et l'écouteur
    public RouteAdapter(ArrayList<Route> routes, OnRouteClickListener listener) {
        this.routes = routes;
        this.listener = listener;
    }

    // Crée la vue d'un élément de liste
    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(view);
    }

    // Remplit la vue avec les données d'une route
    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        Route route = routes.get(position);
        holder.txtNumeroRoute.setText("Route " + route.getNumero());

        // Affiche le nom du livreur ou un message si pas de livreur
        if (route.getNumero() == 0) {
            holder.txtNomLivreurRoute.setText("Pas de route");
        } else if (route.getNomLivreur() == null || route.getNomLivreur().isEmpty()) {
            holder.txtNomLivreurRoute.setText("Pas de livreur");
        } else {
            holder.txtNomLivreurRoute.setText(route.getNomLivreur());
        }

        // Détecte le clic sur cet élément et prévient l'écouteur
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRouteClick(route);
            }
        });
    }

    // Retourne le nombre d'éléments à afficher
    @Override
    public int getItemCount() {
        return routes.size();
    }

    // ViewHolder : contient les vues pour chaque élément de route
    static class RouteViewHolder extends RecyclerView.ViewHolder {
        TextView txtNumeroRoute;     // Affiche le numéro de la route
        TextView txtNomLivreurRoute; // Affiche le nom du livreur

        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNumeroRoute = itemView.findViewById(R.id.txtNumeroRoute);
            txtNomLivreurRoute = itemView.findViewById(R.id.txtNomLivreurRoute);
        }
    }
}
