package com.example.livraison;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Activité pour ajouter ou supprimer une route
public class AjouterSupprimerRoute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On associe cette activité au layout activity_ajouter_supprimer_route.xml
        setContentView(R.layout.activity_ajouter_supprimer_route);

        // Récupération des composants graphiques
        Button btnAjouterRoute = findViewById(R.id.btnAjouterRoute);
        TextView txtMessageAjout = findViewById(R.id.txtMessageAjout);
        EditText edtNumeroRoute = findViewById(R.id.edtNumeroRoute);
        Button btnSupprimerRoute = findViewById(R.id.btnSupprimerRoute);
        TextView txtMessageSuppression = findViewById(R.id.txtMessageSuppression);
        Button btnRetourMenu = findViewById(R.id.btnRetourMenu);

        // Bouton pour ajouter une nouvelle route
        btnAjouterRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le nouveau numéro de route est basé sur la taille de la liste des routes
                int nouveauNumero = GestionnaireDonnees.getInstance().getRoutes().size();
                Route nouvelleRoute = new Route(nouveauNumero);
                // On ajoute la nouvelle route à la liste globale
                GestionnaireDonnees.getInstance().getRoutes().add(nouvelleRoute);

                // Affichage d'un message de confirmation
                txtMessageAjout.setText("Route numéro " + nouveauNumero + " créée avec succès");
                txtMessageAjout.setVisibility(View.VISIBLE);
            }
        });

        // Bouton pour supprimer une route
        btnSupprimerRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroStr = edtNumeroRoute.getText().toString();

                // Vérification que l'utilisateur a saisi un numéro
                if (numeroStr.isEmpty()) {
                    txtMessageSuppression.setText("Veuillez entrer un numéro de route");
                    txtMessageSuppression.setVisibility(View.VISIBLE);
                    return;
                }

                int numero = Integer.parseInt(numeroStr);

                // On ne peut pas supprimer la route 0
                if (numero == 0) {
                    txtMessageSuppression.setText("Impossible de supprimer la route 0");
                    txtMessageSuppression.setVisibility(View.VISIBLE);
                    return;
                }

                // Recherche de la route à supprimer
                Route route = GestionnaireDonnees.getInstance().trouverRoute(numero);
                if (route == null) {
                    txtMessageSuppression.setText("Cette route n'existe pas");
                    txtMessageSuppression.setVisibility(View.VISIBLE);
                    return;
                }

                // Déplacement des abonnements de la route supprimée vers la route 0
                Route route0 = GestionnaireDonnees.getInstance().trouverRoute(0);
                for (Abonnement ab : route.getAbonnements()) {
                    ab.setNumeroRoute(0); // On met la route à 0
                    route0.ajouterAbonnement(ab); // On ajoute l'abonnement à la route 0
                }

                // Suppression de la route de la liste globale
                GestionnaireDonnees.getInstance().getRoutes().remove(route);

                // Affichage d'un message de confirmation
                txtMessageSuppression.setText("Route " + numero + " supprimée avec succès");
                txtMessageSuppression.setVisibility(View.VISIBLE);

                // Réinitialisation du champ de saisie
                edtNumeroRoute.setText("");
            }
        });

        // Bouton pour revenir au menu précédent
        btnRetourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Ferme cette activité et revient à l'écran précédent
            }
        });
    }
}
