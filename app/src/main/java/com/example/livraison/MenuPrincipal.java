package com.example.livraison;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// Activité principale qui sert de menu pour toutes les actions de l'application
public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Récupération des boutons du layout
        Button btnAjouterSupprimerRoute = findViewById(R.id.btnAjouterSupprimerRoute);
        Button btnAffecterRoute = findViewById(R.id.btnAffecterRoute);
        Button btnAjouterAbonnement = findViewById(R.id.btnAjouterAbonnement);
        Button btnRetirerAbonnement = findViewById(R.id.btnRetirerAbonnement);
        Button btnAjouterLivreur = findViewById(R.id.btnAjouterLivreur);
        Button btnLister = findViewById(R.id.btnLister);
        Button btnQuitter = findViewById(R.id.btnQuitter);

        // Actions des boutons : ouverture des activités correspondantes
        btnAjouterSupprimerRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, AjouterSupprimerRoute.class));
            }
        });

        btnAffecterRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, AffecterRoute.class));
            }
        });

        btnAjouterAbonnement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, AjouterAbonnement.class));
            }
        });

        btnRetirerAbonnement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, RetirerAbonnement.class));
            }
        });

        btnAjouterLivreur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, AjouterLivreur.class));
            }
        });

        btnLister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, ListerActivity.class));
            }
        });

        // Bouton quitter : sauvegarde des données avant de fermer l'application
        btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestionnaireDonnees.getInstance().sauvegarderDonnees(MenuPrincipal.this);
                finishAffinity(); // Ferme toutes les activités
            }
        });

        // Vérifie si le bouton "Ajouter livreur" doit être activé
        verifierEtatBoutonLivreur();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Vérifie à nouveau l'état du bouton après retour sur ce menu
        verifierEtatBoutonLivreur();
    }

    // Méthode qui désactive le bouton si toutes les routes ont déjà un livreur
    private void verifierEtatBoutonLivreur() {
        Button btnAjouterLivreur = findViewById(R.id.btnAjouterLivreur);
        btnAjouterLivreur.setEnabled(!GestionnaireDonnees.getInstance().toutesRoutesOntLivreur());
    }
}
