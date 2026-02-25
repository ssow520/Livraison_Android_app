package com.example.livraison;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

// Activité pour lister différents éléments : livreurs, abonnés, routes et produits
public class ListerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On associe cette activité au layout activity_lister.xml
        setContentView(R.layout.activity_lister);

        // Récupération des boutons
        Button btnLivreurs = findViewById(R.id.btnLivreurs);
        Button btnAbonnes = findViewById(R.id.btnAbonnes);
        Button btnRoutes = findViewById(R.id.btnRoutes);
        Button btnProduits = findViewById(R.id.btnProduits);
        Button btnQuitterListe = findViewById(R.id.btnQuitterListe);

        // Affiche le fragment des livreurs
        btnLivreurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargerFragment(new LivreursFragment());
            }
        });

        // Affiche le fragment des abonnés
        btnAbonnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargerFragment(new AbonnesFragment());
            }
        });

        // Affiche le fragment des routes
        btnRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargerFragment(new RoutesFragment());
            }
        });

        // Affiche le fragment des produits
        btnProduits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargerFragment(new ProduitsFragment());
            }
        });

        // Quitte l'activité et retourne à l'écran précédent
        btnQuitterListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Méthode pour remplacer le fragment affiché dans le container
    private void chargerFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment) // Remplace le fragment actuel
                .commit(); // Valide la transaction
    }
}
