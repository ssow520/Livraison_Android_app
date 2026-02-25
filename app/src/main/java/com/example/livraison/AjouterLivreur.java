package com.example.livraison;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Activité pour ajouter un nouveau livreur
public class AjouterLivreur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On associe cette activité au layout activity_ajouter_livreur.xml
        setContentView(R.layout.activity_ajouter_livreur);

        // Récupération des composants graphiques du layout
        EditText edtNom = findViewById(R.id.edtNom);
        EditText edtAdresse = findViewById(R.id.edtAdresse);
        EditText edtTelephone = findViewById(R.id.edtTelephone);
        Button btnEnregistrer = findViewById(R.id.btnEnregistrerLivreur);
        Button btnRetourMenu = findViewById(R.id.btnRetourMenuLivreur);

        // Bouton pour enregistrer un livreur
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des valeurs saisies par l'utilisateur
                String nom = edtNom.getText().toString();
                String adresse = edtAdresse.getText().toString();
                String telephone = edtTelephone.getText().toString();

                // Vérification que tous les champs sont remplis
                if (nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty()) {
                    Toast.makeText(AjouterLivreur.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérification qu'il n'existe pas déjà un livreur avec ce nom
                if (GestionnaireDonnees.getInstance().trouverLivreur(nom) != null) {
                    Toast.makeText(AjouterLivreur.this, "Un livreur avec ce nom existe déjà", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Création du livreur
                Livreur livreur = new Livreur(nom, adresse, telephone);
                // Ajout du livreur à la liste globale
                GestionnaireDonnees.getInstance().getLivreurs().add(livreur);

                // Message de confirmation
                Toast.makeText(AjouterLivreur.this, "Livreur enregistré avec succès", Toast.LENGTH_SHORT).show();

                // Réinitialisation des champs pour un nouvel enregistrement
                edtNom.setText("");
                edtAdresse.setText("");
                edtTelephone.setText("");
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
