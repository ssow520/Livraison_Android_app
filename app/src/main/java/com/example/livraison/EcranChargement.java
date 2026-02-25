package com.example.livraison;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

// Écran de chargement (splash screen) qui s'affiche au démarrage de l'application
public class EcranChargement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On associe cette activité au layout activity_ecran_chargement.xml
        setContentView(R.layout.activity_ecran_chargement);

        // Chargement des données depuis le GestionnaireDonnees (abonnements, routes, livreurs)
        GestionnaireDonnees.getInstance().chargerDonnees(this);

        // On attend 2 secondes avant de passer à l'écran d'accueil
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Création de l'intent pour passer à l'écran d'accueil
                Intent intent = new Intent(EcranChargement.this, EcranAccueil.class);
                startActivity(intent); // Lancement de l'écran d'accueil
                finish(); // Fermeture de l'écran de chargement
            }
        }, 2000); // délai en millisecondes (2000 ms = 2 secondes)
    }
}
