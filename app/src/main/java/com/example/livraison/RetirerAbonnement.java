package com.example.livraison;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Activité pour rechercher et supprimer un abonnement existant
public class RetirerAbonnement extends AppCompatActivity {

    private Abonnement abonnementAffiche; // L'abonnement actuellement affiché à l'écran

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retirer_abonnement);

        EditText edtNumeroAbonnement = findViewById(R.id.edtNumeroAbonnement);
        Button btnAfficher = findViewById(R.id.btnAfficher);
        TextView txtDetailsAbonnement = findViewById(R.id.txtDetailsAbonnement);
        Button btnSupprimer = findViewById(R.id.btnSupprimer);
        Button btnRetourMenu = findViewById(R.id.btnRetourMenu);

        // Bouton pour afficher les détails d'un abonnement à partir de son numéro
        btnAfficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroStr = edtNumeroAbonnement.getText().toString();
                if (numeroStr.isEmpty()) {
                    Toast.makeText(RetirerAbonnement.this, "Veuillez entrer un numéro", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numero = Integer.parseInt(numeroStr);
                abonnementAffiche = GestionnaireDonnees.getInstance().trouverAbonnement(numero);

                if (abonnementAffiche == null) {
                    txtDetailsAbonnement.setText("Aucun abonnement trouvé avec ce numéro");
                    txtDetailsAbonnement.setVisibility(View.VISIBLE);
                    return;
                }

                // Affiche les détails complets de l'abonnement
                String details = "Abonnement #" + abonnementAffiche.getNumero() + "\n" +
                        "Client: " + abonnementAffiche.getNomClient() + "\n" +
                        "Adresse: " + abonnementAffiche.getAdresse() + "\n" +
                        "Magazines: " + abonnementAffiche.getQuantiteMagazines() + "\n" +
                        "Journaux: " + abonnementAffiche.getQuantiteJournaux() + "\n" +
                        "Route: " + abonnementAffiche.getNumeroRoute();

                txtDetailsAbonnement.setText(details);
                txtDetailsAbonnement.setVisibility(View.VISIBLE);
            }
        });

        // Bouton pour supprimer l'abonnement affiché
        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (abonnementAffiche == null) {
                    Toast.makeText(RetirerAbonnement.this, "Aucun abonnement sélectionné", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Retire l'abonnement de la route associée si elle existe
                Route route = GestionnaireDonnees.getInstance().trouverRoute(abonnementAffiche.getNumeroRoute());
                if (route != null) {
                    route.retirerAbonnement(abonnementAffiche);
                }

                // Retire l'abonnement de la liste générale
                GestionnaireDonnees.getInstance().getAbonnements().remove(abonnementAffiche);
                Toast.makeText(RetirerAbonnement.this, "Abonnement supprimé", Toast.LENGTH_SHORT).show();

                // Réinitialisation des champs
                txtDetailsAbonnement.setVisibility(View.GONE);
                edtNumeroAbonnement.setText("");
                abonnementAffiche = null;
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
