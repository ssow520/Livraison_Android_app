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

// Fragment pour afficher toutes les routes et leurs abonnements
public class RoutesFragment extends Fragment {

    private RecyclerView recyclerViewRoutes;      // Liste des routes
    private GridView gridViewDetailsRoute;        // Détails des abonnements d'une route
    private TextView txtDetailsRoute;             // Message si pas d'abonnement

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // On "gonfle" le layout du fragment
        View view = inflater.inflate(R.layout.fragment_routes, container, false);

        // On récupère les vues
        recyclerViewRoutes = view.findViewById(R.id.recyclerViewRoutes);
        gridViewDetailsRoute = view.findViewById(R.id.gridViewDetailsRoute);
        txtDetailsRoute = view.findViewById(R.id.txtDetailsRoute);

        // On définit le layout du RecyclerView
        recyclerViewRoutes.setLayoutManager(new LinearLayoutManager(getContext()));

        // On récupère la liste des routes depuis le gestionnaire de données
        ArrayList<Route> routes = GestionnaireDonnees.getInstance().getRoutes();

        // On crée l'adapter pour afficher les routes
        RouteAdapter adapter = new RouteAdapter(routes, new RouteAdapter.OnRouteClickListener() {
            @Override
            public void onRouteClick(Route route) {
                // Quand on clique sur une route, on affiche ses abonnements
                afficherDetailsRoute(route);
            }
        });
        recyclerViewRoutes.setAdapter(adapter);

        return view;
    }

    // Méthode pour afficher les abonnements d'une route dans la GridView
    private void afficherDetailsRoute(Route route) {
        if (route.getAbonnements().isEmpty()) {
            // Si pas d'abonnements, on affiche un message
            txtDetailsRoute.setText("Aucun abonnement sur cette route");
            txtDetailsRoute.setVisibility(View.VISIBLE);
            gridViewDetailsRoute.setVisibility(View.GONE);
            return;
        }

        // Sinon, on cache le message et on montre la GridView
        txtDetailsRoute.setVisibility(View.GONE);
        gridViewDetailsRoute.setVisibility(View.VISIBLE);

        // On prépare les détails à afficher
        ArrayList<String> details = new ArrayList<>();
        for (Abonnement ab : route.getAbonnements()) {
            String detail = ab.getAdresse() + "\n" +
                    "Client: " + ab.getNomClient() + "\n" +
                    "Magazines: " + ab.getQuantiteMagazines() + "\n" +
                    "Journaux: " + ab.getQuantiteJournaux();
            details.add(detail);
        }

        // On crée un adapter pour la GridView avec les détails
        DetailsAdapter adapter = new DetailsAdapter(getContext(), details);
        gridViewDetailsRoute.setAdapter(adapter);
    }
}
