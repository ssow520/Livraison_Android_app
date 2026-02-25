package com.example.livraison;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Activité permettant de retirer un livreur d'une route
public class RetirerLivreur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retirer_livreur);

        EditText edtNomLivreur = findViewById(R.id.edtNomLivreur);
        Button btnSupprimerLivreur = findViewById(R.id.btnSupprimerLivreur);
        TextView txtMessage = findViewById(R.id.txtMessageLivreur);
        Button btnRetourMenu = findViewById(R.id.btnRetourMenuRetirerLivreur);

        // Bouton pour retirer le livreur de sa route
        btnSupprimerLivreur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = edtNomLivreur.getText().toString();

                if (nom.isEmpty()) {
                    txtMessage.setText("Veuillez entrer un nom de livreur");
                    txtMessage.setVisibility(View.VISIBLE);
                    return;
                }

                // Recherche du livreur
                Livreur livreur = GestionnaireDonnees.getInstance().trouverLivreur(nom);
                if (livreur == null) {
                    txtMessage.setText("Ce livreur n'existe pas");
                    txtMessage.setVisibility(View.VISIBLE);
                    return;
                }

                // Retrait du livreur de sa route s'il est affecté
                int numeroRoute = livreur.getNumeroRoute();
                if (numeroRoute != 0) {
                    Route route = GestionnaireDonnees.getInstance().trouverRoute(numeroRoute);
                    if (route != null) {
                        route.setNomLivreur(null); // Supprime l'affectation du livreur à la route
                    }
                    livreur.setNumeroRoute(0); // Remet le numéro de route du livreur à 0
                }

                txtMessage.setText("Livreur " + nom + " retiré de sa route avec succès");
                txtMessage.setVisibility(View.VISIBLE);
                edtNomLivreur.setText("");
            }
        });

        // Retour au menu principal
        btnRetourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
