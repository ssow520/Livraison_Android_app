package com.example.livraison;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Activité pour affecter ou retirer un livreur d'une route
public class AffecterRoute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On associe cette activité au layout activity_affecter_route.xml
        setContentView(R.layout.activity_affecter_route);

        // Récupération des composants graphiques du layout
        EditText edtNomLivreur = findViewById(R.id.edtNomLivreur);
        EditText edtNumeroRoute = findViewById(R.id.edtNumeroRoute);
        Button btnAffecter = findViewById(R.id.btnAffecter);
        Button btnRetirer = findViewById(R.id.btnRetirer);
        TextView txtMessage = findViewById(R.id.txtMessage);
        Button btnRetourMenu = findViewById(R.id.btnRetourMenu);

        // Bouton pour affecter un livreur à une route
        btnAffecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomLivreur = edtNomLivreur.getText().toString();
                String numeroStr = edtNumeroRoute.getText().toString();

                // Vérification que tous les champs sont remplis
                if (nomLivreur.isEmpty() || numeroStr.isEmpty()) {
                    txtMessage.setText("Veuillez remplir tous les champs");
                    txtMessage.setVisibility(View.VISIBLE);
                    return;
                }

                // Conversion du numéro de route en entier
                int numeroRoute = Integer.parseInt(numeroStr);

                // On récupère le livreur et la route correspondants
                Livreur livreur = GestionnaireDonnees.getInstance().trouverLivreur(nomLivreur);
                Route route = GestionnaireDonnees.getInstance().trouverRoute(numeroRoute);

                // Vérification que le livreur existe
                if (livreur == null) {
                    txtMessage.setText("Ce livreur n'existe pas");
                    txtMessage.setVisibility(View.VISIBLE);
                    return;
                }

                // Vérification que la route existe
                if (route == null) {
                    txtMessage.setText("Cette route n'existe pas");
                    txtMessage.setVisibility(View.VISIBLE);
                    return;
                }

                // On ne peut pas affecter à la route 0
                if (numeroRoute == 0) {
                    txtMessage.setText("Impossible d'affecter à la route 0");
                    txtMessage.setVisibility(View.VISIBLE);
                    return;
                }

                // Affectation du livreur à la route
                livreur.setNumeroRoute(numeroRoute);
                route.setNomLivreur(nomLivreur);

                // Affichage d'un message de confirmation
                txtMessage.setText("Livreur " + nomLivreur + " affecté à la route " + numeroRoute);
                txtMessage.setVisibility(View.VISIBLE);
            }
        });

        // Bouton pour retirer un livreur d'une route
        btnRetirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomLivreur = edtNomLivreur.getText().toString();
                String numeroStr = edtNumeroRoute.getText().toString();

                // Vérification que tous les champs sont remplis
                if (nomLivreur.isEmpty() || numeroStr.isEmpty()) {
                    txtMessage.setText("Veuillez remplir tous les champs");
                    txtMessage.setVisibility(View.VISIBLE);
                    return;
                }

                int numeroRoute = Integer.parseInt(numeroStr);

                // On récupère le livreur et la route
                Livreur livreur = GestionnaireDonnees.getInstance().trouverLivreur(nomLivreur);
                Route route = GestionnaireDonnees.getInstance().trouverRoute(numeroRoute);

                // Vérification que le livreur et la route existent
                if (livreur == null || route == null) {
                    txtMessage.setText("Livreur ou route invalide");
                    txtMessage.setVisibility(View.VISIBLE);
                    return;
                }

                // Retirer le livreur de la route
                livreur.setNumeroRoute(0); // 0 signifie pas de route
                route.setNomLivreur(null);

                // Affichage d'un message de confirmation
                txtMessage.setText("Livreur " + nomLivreur + " retiré de la route " + numeroRoute);
                txtMessage.setVisibility(View.VISIBLE);
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
