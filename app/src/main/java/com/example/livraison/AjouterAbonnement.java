package com.example.livraison;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Activité pour ajouter un nouvel abonnement
public class AjouterAbonnement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On associe cette activité au layout activity_ajouter_abonnement.xml
        setContentView(R.layout.activity_ajouter_abonnement);

        // Récupération des composants graphiques du layout
        EditText edtNomClient = findViewById(R.id.edtNomClient);
        EditText edtAdresse = findViewById(R.id.edtAdresse);
        EditText edtQuantiteMagazines = findViewById(R.id.edtQuantiteMagazines);
        EditText edtQuantiteJournaux = findViewById(R.id.edtQuantiteJournaux);
        EditText edtNumeroRoute = findViewById(R.id.edtNumeroRoute);
        Button btnEnregistrer = findViewById(R.id.btnEnregistrer);
        Button btnRetourMenu = findViewById(R.id.btnRetourMenu);

        // Dès que l'utilisateur quitte le champ adresse, on suggère une route automatiquement
        edtAdresse.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !edtAdresse.getText().toString().isEmpty()) {
                    int routeSuggeree = suggererRoute(edtAdresse.getText().toString());
                    edtNumeroRoute.setText(String.valueOf(routeSuggeree));
                }
            }
        });

        // Bouton pour enregistrer un abonnement
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Récupération des valeurs saisies par l'utilisateur
                String nom = edtNomClient.getText().toString();
                String adresse = edtAdresse.getText().toString();
                String qtyMagStr = edtQuantiteMagazines.getText().toString();
                String qtyJourStr = edtQuantiteJournaux.getText().toString();
                String routeStr = edtNumeroRoute.getText().toString();

                // Vérification des champs obligatoires
                if (nom.isEmpty() || adresse.isEmpty() || routeStr.isEmpty()) {
                    Toast.makeText(AjouterAbonnement.this,
                            "Veuillez remplir tous les champs obligatoires",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Conversion des quantités en entier (0 si vide)
                int qtyMag = qtyMagStr.isEmpty() ? 0 : Integer.parseInt(qtyMagStr);
                int qtyJour = qtyJourStr.isEmpty() ? 0 : Integer.parseInt(qtyJourStr);
                int numeroRoute = Integer.parseInt(routeStr);

                // Vérification qu'au moins un produit est commandé
                if (qtyMag == 0 && qtyJour == 0) {
                    Toast.makeText(AjouterAbonnement.this,
                            "Veuillez indiquer au moins un produit",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérification que la route existe
                Route route = GestionnaireDonnees.getInstance().trouverRoute(numeroRoute);
                if (route == null) {
                    Toast.makeText(AjouterAbonnement.this,
                            "Route invalide",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Création de l'abonnement
                Abonnement abonnement = new Abonnement(
                        nom,
                        adresse,
                        qtyMag,
                        qtyJour,
                        numeroRoute
                );

                // Ajout de l'abonnement à la liste générale et à la route correspondante
                GestionnaireDonnees.getInstance().getAbonnements().add(abonnement);
                route.ajouterAbonnement(abonnement);

                // Message de confirmation
                Toast.makeText(AjouterAbonnement.this,
                        "Abonnement enregistré avec succès",
                        Toast.LENGTH_SHORT).show();

                // Réinitialisation des champs pour un nouvel enregistrement
                edtNomClient.setText("");
                edtAdresse.setText("");
                edtQuantiteMagazines.setText("");
                edtQuantiteJournaux.setText("");
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

    // Méthode simple pour suggérer une route selon la longueur de l'adresse
    private int suggererRoute(String adresse) {
        int nbRoutes = GestionnaireDonnees.getInstance().getRoutes().size();
        if (nbRoutes == 0) return 0; // Pas de route disponible

        int valeur = adresse.length();
        // On retourne un numéro de route basé sur le reste de la division
        return (valeur % nbRoutes) + 1;
    }
}
